package mk.ukim.finki.wp.labvezba.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;

    @OneToMany(mappedBy = "chef", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Dish> Dishes=new ArrayList<>();

    public Chef(String firstName,String lastName,String bio){
        this.firstName=firstName;
        this.lastName=lastName;
        this.bio=bio;
    }
}
