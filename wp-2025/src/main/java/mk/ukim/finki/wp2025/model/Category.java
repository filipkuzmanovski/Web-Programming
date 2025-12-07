package mk.ukim.finki.wp2025.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Kategorijata ke ni pretstavuva zbir od atributi
//Vo model layerot ni stojat site klasi kojshto gi koristi nekoja aplikacija
public class Category{
    private String name;
    private String description;

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }
}
