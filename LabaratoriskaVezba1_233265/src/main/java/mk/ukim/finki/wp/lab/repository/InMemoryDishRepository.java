package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class InMemoryDishRepository implements DishRepository {

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        for(Dish d:DataHolder.dishes){
            if(d.getDishId().equals(dishId)){
                return d;
            }
        }
        return null;
    }
    public Dish save(Dish dish) {
        DataHolder.dishes.add(dish);
        return dish;
    }
}
