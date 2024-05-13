package og.dz11.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import org.springframework.context.annotation.Profile;

//Dodavanje novog entiteta, neophodno je dodati i anotaciju profil kako skripte
//za kreiranje koristie semu OLYMPICS za kreiranje tabela
@Entity
@Profile("OLYMPICS")
public class Event {
    @Column(name = "id")
    @jakarta.persistence.Id
    @SequenceGenerator(name = "EntityTwoSequence", initialValue = 1000)
    @GeneratedValue(generator = "EntityTwoSequence")
    public Long Id;

    @Column(name = "event_name")
    public String Name;
}
