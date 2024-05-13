package og.dz11.configuration;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableWebSecurity
@EnableJms
public class AppConfig {
    private final Environment environment;

    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName(environment.getProperty("db.name"))
                .setType(EmbeddedDatabaseType.H2)
                .addScripts(
                        "01_reference_data.sql",
                        "02_event.sql",
                        "03_games.sql",
                        "04_games_city.sql",
                        "05_person.sql",
                        "06_person_region.sql",
                        "07_games_competitor.sql",
                        "08_competitor_event.sql",
                        // Dodavanje user i autorizaconih tabela
                        "09_user.sql")
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        // Ignorisanje matchere za csrf za pristup h2 konzoli
                        .ignoringRequestMatchers("/console/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests(authorize -> authorize
                        //Za pristup konozli samo administranorti
                        .requestMatchers("/console/**").hasRole("ADMIN")
                        //Ostatak korisnici
                        .requestMatchers("/city/**", "/event/**", "/games/**", "/jms/**").hasRole("USER")
                        //Svi pristup error stranici
                        .requestMatchers("/error/**").permitAll()
                        //Zahteva autentifikaciju za sve pozive
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        // Podesavanja za header-e neophodni za rad /console in memory baze
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                //Koriscenje osnovnih http pravilia pristupa
                .httpBasic(withDefaults())
                //Dodavanje logovanje formom kao i logout
                .formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        //dodavanje user naloga
        //withDefaultPasswordEncoder automatski koristi hesiranje pasworda po defaultu
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        //dodavanje admin naloga
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        //koriscenje postojece baze za upis i citanje korisnika
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        users.createUser(user);
        users.createUser(admin);

        return users;
    }

    // Konfigurisanje produkcije konekcija
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return connectionFactory;
    }

    //Konverter
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

    // Kreariranje jms tempalte-a sa produkcijom konekcija
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName("queue");
        return template;
    }

    // Podrazumevani jms osluskivac poruka
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("10");
        return factory;
    }
}
