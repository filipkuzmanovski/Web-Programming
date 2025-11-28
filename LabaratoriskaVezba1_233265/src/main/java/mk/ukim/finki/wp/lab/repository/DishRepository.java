package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
}
