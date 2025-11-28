package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }
    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        List<Dish> dishes = dishService.listDishes();
        model.addAttribute("dishes", dishes);
        model.addAttribute("error", error);
        return "listDishes";
    }
    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        Dish emptyDish = new Dish(null, "", "",  0);
        model.addAttribute("dish", emptyDish);
        return "dishForm";
    }
    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);
        if (dish == null) {
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish", dish);
        return "dishForm";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam(required = false) Long chefId) {
        Dish dish = dishService.create(dishId, name, cuisine, preparationTime);

        if (chefId != null) {
            chefService.addDishToChef(chefId, dish.getDishId());
        }
        return "redirect:/dishes";
    }
    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }
    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }
}
