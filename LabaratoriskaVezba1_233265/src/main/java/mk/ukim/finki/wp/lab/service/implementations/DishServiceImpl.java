package mk.ukim.finki.wp.lab.service.implementations;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.LabExceptions.DishNotFoundException;
import mk.ukim.finki.wp.lab.repository.interfaces.ChefRepository;
import mk.ukim.finki.wp.lab.repository.interfaces.DishRepository;
import mk.ukim.finki.wp.lab.service.interfaces.ChefService;
import mk.ukim.finki.wp.lab.service.interfaces.DishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final ChefService chefService;

    DishServiceImpl(DishRepository dishRepository, ChefService chefService) {
        this.dishRepository = dishRepository;
        this.chefService = chefService;
    }
    @Override
    public List<Dish> listDishes(Long chefId) {
        if(chefId == null) {
            return dishRepository.findAll();
        }else {
            return dishRepository.findAllByChefId(chefId);
        }
    }

    @Override
    public Dish findByDishId(String dishId) {

        return dishRepository.findByDishId(dishId).get();
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime,Long chefId) {
        Dish dish=new Dish(dishId,name,cuisine,preparationTime);
        Chef chef = chefService.findById(chefId);
        dish.setChef(chef);
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime,Long chefId) {
        Dish targetDish=dishRepository.findById(id).orElseThrow(DishNotFoundException::new);
        Chef chef = chefService.findById(chefId);
        targetDish.setDishId(dishId);
        targetDish.setName(name);
        targetDish.setCuisine(cuisine);
        targetDish.setPreparationTime(preparationTime);
        targetDish.setChef(chef);
        return dishRepository.save(targetDish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

}
