//package mk.ukim.finki.wp.labvezba.repository.implementations;
//
//import mk.ukim.finki.wp.labvezba.bootstrap.DataHolder;
//import mk.ukim.finki.wp.labvezba.model.Dish;
//import mk.ukim.finki.wp.labvezba.repository.interfaces.DishRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class InMemoryDishRepository implements DishRepository {
//
//    @Override
//    public List<Dish> findAll() {
//        return DataHolder.dishes;
//    }
//
//    @Override
//    public Dish findByDishId(String dishId) {
//        return DataHolder.dishes.stream().filter(d->d.getDishId().equals(dishId)).findFirst().orElseThrow(()->new RuntimeException("No dish Found"));
//    }
//
//    @Override
//    public Optional<Dish> findById(Long id) {
//        return DataHolder.dishes.stream().filter(d->d.getId().equals(id)).findFirst();
//    }
//
//    @Override
//    public Dish save(Dish dish) {
//        DataHolder.dishes.removeIf(d->d.getId().equals(dish.getId()));
//        DataHolder.dishes.add(dish);
//        return dish;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        DataHolder.dishes.removeIf(d->d.getId().equals(id));
//    }
//}
