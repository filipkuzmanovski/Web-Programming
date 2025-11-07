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
    @PostConstruct
    public void initData() {
        if (chefRepo.findAll().isEmpty() && dishRepo.findAll().isEmpty()) {
            InMemoryDishRepository dishRepoImpl = (InMemoryDishRepository) dishRepo;
            Dish d1 = new Dish("d1", "Spaghetti", "Italian", 30);
            Dish d2 = new Dish("d2", "Sushi", "Japanese", 20);
            Dish d3 = new Dish("d3", "Burger", "American", 15);

            dishRepoImpl.save(d1);
            dishRepoImpl.save(d2);
            dishRepoImpl.save(d3);

            Chef c1 = new Chef(1L, "Gordon", "Ramsay", "Famous British chef", new ArrayList<>());
            Chef c2 = new Chef(2L, "Jamie", "Oliver", "Known for healthy recipes", new ArrayList<>());
            Chef c3 = new Chef(3L, "Alice", "Waters", "Pioneer of organic cooking", new ArrayList<>());

            chefRepo.save(c1);
            chefRepo.save(c2);
            chefRepo.save(c3);
        }
    }
}
