package mk.ukim.finki.wp.labvezba.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.labvezba.model.Chef;
import mk.ukim.finki.wp.labvezba.model.Dish;
import mk.ukim.finki.wp.labvezba.repository.interfaces.ChefRepository;
import mk.ukim.finki.wp.labvezba.repository.interfaces.DishRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs=new ArrayList<>(5);
    public static List<Dish> dishes=new ArrayList<>(5);

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DataHolder(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @PostConstruct
    public void init(){


        Chef c1=new Chef("Mario", "Rossi", "Italian cuisine specialist");
        Chef c2=new Chef("Sakura", "Tanaka", "Expert in sushi and Japanese dishes");
        Chef c3=new Chef("Arjun", "Patel", "Passionate about Indian spices");
        Chef c4=new Chef("Carlos", "Gomez", "Mexican street food expert");
        Chef c5=new Chef("Nok", "Sukhum", "Thai cuisine chef specializing in stir-fried dishes");

        chefRepository.save(c1);
        chefRepository.save(c2);
        chefRepository.save(c3);
        chefRepository.save(c4);
        chefRepository.save(c5);

        dishRepository.save(new Dish("D001", "Spaghetti Carbonara", "Italian", 25,c1));
        dishRepository.save(new Dish("D002", "Sushi Platter", "Japanese", 40,c2));
        dishRepository.save(new Dish("D003", "Chicken Tikka Masala", "Indian", 50,c3));
        dishRepository.save(new Dish("D004", "Beef Tacos", "Mexican", 20,c4));
        dishRepository.save(new Dish("D005", "Pad Thai", "Thai", 30,c5));
    }
}
