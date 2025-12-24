package mk.ukim.finki.wp.labvezba.service.implementations;

import mk.ukim.finki.wp.labvezba.model.Chef;
import mk.ukim.finki.wp.labvezba.model.Dish;
import mk.ukim.finki.wp.labvezba.repository.interfaces.ChefRepository;
import mk.ukim.finki.wp.labvezba.repository.interfaces.DishRepository;
import mk.ukim.finki.wp.labvezba.service.interfaces.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public ChefServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElseThrow(()->new RuntimeException("Chef wasn't found"));
    }

    @Override
    public Chef addDishToChef(Long chefId, Long dishId) {
        Chef chef=chefRepository.findById(chefId).orElseThrow(()->new RuntimeException("Chef wasn't found"));
        Dish dish=dishRepository.findById(dishId).orElseThrow(()->new RuntimeException("Dish wasn't found"));

        if(chef.getDishes().contains(dish)){
            return chefRepository.save(chef);
        }


        dish.setChef(chef);
        dishRepository.save(dish);
        return chef;
    }
}
