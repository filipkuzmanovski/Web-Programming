package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<Chef> chefs=new ArrayList<>(5);
    public static List<Dish> dishes=new ArrayList<>(5);

    @PostConstruct
    public void init(){
        chefs.add(new Chef(2, "Jamie", "Oliver", "Known for simple and healthy cooking", new ArrayList<>()));
        chefs.add(new Chef(3, "Massimo", "Bottura", "Italian chef famous for modern Italian cuisine", new ArrayList<>()));
        chefs.add(new Chef(4, "Heston", "Blumenthal", "Expert in molecular gastronomy", new ArrayList<>()));
        chefs.add(new Chef(5, "Thomas", "Keller", "American chef with multiple Michelin stars", new ArrayList<>()));
        chefs.add(new Chef(1,"Gordon","Ramsay","Greatest chef ever",new ArrayList<>()));

        dishes.add(new Dish("D1", "Beef Wellington", "British", 120));
        dishes.add(new Dish("D2", "Ravioli al Tartufo", "Italian", 45));
        dishes.add(new Dish("D3", "Sushi Moriawase", "Japanese", 50));
        dishes.add(new Dish("D4", "French Onion Soup", "French", 60));
        dishes.add(new Dish("D5", "Chicken Tikka Masala", "Indian", 40));

    }
}
