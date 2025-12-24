package mk.ukim.finki.wp.labvezba.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Chef chef;

    public Dish(String dishId, String name, String cuisine, int preparationTime,Chef chef) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.chef=chef;
    }
}
