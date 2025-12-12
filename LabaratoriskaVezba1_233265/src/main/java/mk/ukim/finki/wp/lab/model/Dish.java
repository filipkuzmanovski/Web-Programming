package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="dishes")
@NoArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    String dishId;
    String name;
    String cuisine;
    int preparationTime;

    @ManyToOne
    Chef chef;

    public Dish(String dishId,String name,String cuisine,int preparationTime){
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }
}
