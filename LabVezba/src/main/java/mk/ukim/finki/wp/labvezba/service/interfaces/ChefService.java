package mk.ukim.finki.wp.labvezba.service.interfaces;

import mk.ukim.finki.wp.labvezba.model.Chef;

import java.util.List;

public interface ChefService {
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId,Long dishId);
}
