package mk.ukim.finki.wp.vebprogramiranje.bootstrap;

//Vo dataholder klasata ke gi
//cuvame site inicijalizirani
// listi atributi i slicno
//ISTO TAKA GO POVIKUVAME INIT METODOT
//OVA MOMENTALNO E VAKA SAMO
// ZA IN MEMORY ATRIBUTI односно атрибути
// што се чуваат на рам не во база
// SHTO NE PRIPAGJAAT NA KLASA

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;

import mk.ukim.finki.wp.lab.repository.interfaces.ChefRepository;
import mk.ukim.finki.wp.lab.repository.interfaces.DishRepository;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

//za da mozhe da startuva na pocetokot
//na aplikacijata mora dataholder
// klasata da ja anotirame so
// component i vnatre init metodot
// da go anotirame kako postConstruct
// za da mozhe odkoa se kreira klasata
// vednash da se povika metodot init
@Component
public class DataHolder {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;
    public DataHolder(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }
    public static List<Dish> dishes=new ArrayList<>(5);
    public static List<Chef> chefs=new ArrayList<>(5);

    @PostConstruct
    public void init() throws ServletException {

        dishRepository.save(new Dish("D001", "Spaghetti Carbonara", "Italian", 25));
        dishRepository.save(new Dish("D002", "Sushi Platter", "Japanese", 40));
        dishRepository.save(new Dish("D003", "Chicken Tikka Masala", "Indian", 50));
        dishRepository.save(new Dish("D004", "Beef Tacos", "Mexican", 20));
        dishRepository.save(new Dish("D005", "Pad Thai", "Thai", 30));

        chefRepository.save(new Chef( "Mario", "Rossi", "Italian cuisine specialist"));
        chefRepository.save(new Chef("Sakura", "Tanaka", "Expert in sushi and Japanese dishes"));
        chefRepository.save(new Chef( "Arjun", "Patel", "Passionate about Indian spices"));
        chefRepository.save(new Chef("Carlos", "Gomez", "Mexican street food expert"));
        chefRepository.save(new Chef("Nok", "Sukhum", "Thai cuisine chef specializing in stir-fried dishes"));



    }

}
