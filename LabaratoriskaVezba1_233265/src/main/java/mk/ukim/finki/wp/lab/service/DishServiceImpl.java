package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.repository.InMemoryDishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DishServiceImpl  implements DishService {
    private final DishRepository dishRepo;

    public DishServiceImpl(DishRepository dishRepo) {
        this.dishRepo = dishRepo;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepo.findAll();
    }

    public Dish findByDishId(String dishId) {
        return dishRepo.findByDishId(dishId);
    }
}
