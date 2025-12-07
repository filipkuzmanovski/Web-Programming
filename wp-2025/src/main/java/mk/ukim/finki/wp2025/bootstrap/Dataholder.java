package mk.ukim.finki.wp2025.bootstrap;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import mk.ukim.finki.wp2025.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//Vo dataholder klasata ke gi
//cuvame site inicijalizirani
// listi atributi i slicno
//ISTO TAKA GO POVIKUVAME INIT METODOT
//OVA MOMENTALNO E VAKA SAMO
// ZA IN MEMORY ATRIBUTI односно атрибути
// што се чуваат на рам не во база
// SHTO NE PRIPAGJAAT NA KLASA

//za da mozhe da startuva na pocetokot
//na aplikacijata mora dataholder
// klasata da ja anotirame so
// component i vnatre init metodot
// da go anotirame kako postConstruct
// za da mozhe odkoa se kreira klasata
// vednash da se povika metodot init
@Component
public class Dataholder {
    public static List<Category> categoryList = new ArrayList<>();

    @PostConstruct
    public void init() throws ServletException {
        categoryList.add(new Category("Software","Learn about software"));
        categoryList.add(new Category("Books","Who doesn't love books!?"));
    }
}
