package og.dz11.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Profile;

@Entity
@Profile("OLYMPICS")
public class Games {
    @Id
    @Column(name = "id")
    private Long Id;
    @Column(name = "GAMES_YEAR")
    public Integer year;
    @Column(name = "GAMES_NAME")
    public String name;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    public City City;
    @Column(name = "SEASON")
    public String season;
}
