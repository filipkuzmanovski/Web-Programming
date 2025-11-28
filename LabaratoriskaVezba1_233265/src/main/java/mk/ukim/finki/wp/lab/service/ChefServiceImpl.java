package mk.ukim.finki.wp.lab.service;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.repository.InMemoryDishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChefServiceImpl implements ChefService{
    private final DishRepository dishRepo;
    private final ChefRepository chefRepo;

    public ChefServiceImpl(DishRepository dishRepo, ChefRepository chefRepo) {
        this.dishRepo = dishRepo;
        this.chefRepo = chefRepo;
    }

    @Override
    public List<Chef> listChefs(){
        return chefRepo.findAll();
    }

    @Override
    public Chef findById(Long id){
        return chefRepo.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId,String dishId){
        Chef chef=findById(chefId);
        Dish dish=dishRepo.findByDishId(dishId);

        chef.getDishes().add(dish);
        return chefRepo.save(chef);
    }
}
