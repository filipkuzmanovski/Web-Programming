package mk.ukim.finki.wp.lab.model;

import lombok.Getter;

@Getter
public class Dish {
    private String dishId;
    private String name;
    private String cuisine;
    private int preperationTime;

    public Dish(String dishId, String name, String cuisine, int preperationTime) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preperationTime = preperationTime;
    }

}
