package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository {

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream()
                .filter(d -> d.getDishId().equals(dishId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return DataHolder.dishes.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        if(dish.getId() != null) {
            Optional<Dish> existing = findById(dish.getId());
            if(existing.isPresent()) {
                Dish d = existing.get();
                d.setDishId(dish.getDishId());
                d.setName(dish.getName());
                d.setCuisine(dish.getCuisine());
                d.setPreparationTime(dish.getPreparationTime());
                return d;
            }
        }
        DataHolder.dishes.add(dish);
        return dish;
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(DataHolder.dishes::remove);
    }
}
