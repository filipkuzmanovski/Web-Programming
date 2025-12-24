package mk.ukim.finki.wp.labvezba.service.implementations;

import mk.ukim.finki.wp.labvezba.bootstrap.DataHolder;
import mk.ukim.finki.wp.labvezba.model.Chef;
import mk.ukim.finki.wp.labvezba.model.Dish;
import mk.ukim.finki.wp.labvezba.repository.interfaces.ChefRepository;
import mk.ukim.finki.wp.labvezba.repository.interfaces.DishRepository;
import mk.ukim.finki.wp.labvezba.service.interfaces.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElseThrow(()->new RuntimeException("Dish not found"));
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime,Long chefId) {
        Chef chef=chefRepository.findById(chefId).orElseThrow(()->new RuntimeException("Chef not found"));
        Dish dish=new Dish(dishId,name,cuisine,preparationTime,chef);
        dishRepository.save(dish);
        return dish;
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime,Long chefId) {
        Chef chef=chefRepository.findById(chefId).orElseThrow(()->new RuntimeException("Chef not found"));
        Dish targetDish=dishRepository.findById(id).orElseThrow(()->new RuntimeException("Dish not found"));

        targetDish.setDishId(dishId);
        targetDish.setName(name);
        targetDish.setCuisine(cuisine);
        targetDish.setPreparationTime(preparationTime);
        targetDish.setChef(chef);

        return dishRepository.save(targetDish);
    }

    @Override
    public void delete(Long id) {
        Dish dishToRemove=dishRepository.findById(id).orElseThrow(()->new RuntimeException("Dish not found"));
        dishRepository.delete(dishToRemove);
    }
}
