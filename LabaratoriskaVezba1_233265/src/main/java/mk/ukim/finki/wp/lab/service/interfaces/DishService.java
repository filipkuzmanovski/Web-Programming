package mk.ukim.finki.wp.lab.service.interfaces;

import mk.ukim.finki.wp.lab.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {
    List<Dish> listDishes(Long chefId);
    Dish findByDishId(String dishId);
    Optional<Dish> findById(Long id);
    Dish create(String dishId, String name, String cuisine, int preparationTime,Long chefId);
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime,Long chefId);
    void delete(Long id);
}
