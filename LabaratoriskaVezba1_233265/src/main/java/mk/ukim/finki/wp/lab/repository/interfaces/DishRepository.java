package mk.ukim.finki.wp.lab.repository.interfaces;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {
    List<Dish> findAllByChefId(Long chefId);
    Optional<Dish> findByDishId(String dishId);
}
