package mk.ukim.finki.wp.labvezba.repository.interfaces;

import mk.ukim.finki.wp.labvezba.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {
    List<Dish> findAllByChef_Id(Long chefId);
    Dish findByDishId(String dishId);
}
