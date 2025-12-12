package mk.ukim.finki.wp.lab.service.implementations;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.LabExceptions.DishNotFoundException;
import mk.ukim.finki.wp.lab.model.LabExceptions.NotSelectedChefException;
import mk.ukim.finki.wp.lab.repository.interfaces.ChefRepository;
import mk.ukim.finki.wp.lab.repository.interfaces.DishRepository;
import mk.ukim.finki.wp.lab.service.interfaces.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }
    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElseThrow(NotSelectedChefException::new);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef=chefRepository.findById(chefId).orElseThrow(NotSelectedChefException::new);
        Dish dish=dishRepository.findByDishId(dishId).orElseThrow(DishNotFoundException::new);

        if(chef.getDishes().contains(dish)){
            return chefRepository.save(chef);
        }
        chef.getDishes().add(dish);
        dish.setChef(chef);
        return chefRepository.save(chef);
    }
}
