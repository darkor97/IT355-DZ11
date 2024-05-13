package og.dz11.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Entity
@Profile("OLYMPICS")
public class City {
    @Column(name = "id")
    @jakarta.persistence.Id
    public Long Id;
    @Column(name = "city_name")
    public String Name;

    @OneToMany
    public List<Games> GamesHosted;
}
