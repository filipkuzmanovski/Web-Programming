package mk.ukim.finki.wp.lab.web.LabControllers;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.interfaces.ChefService;
import mk.ukim.finki.wp.lab.service.interfaces.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getDishesPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) Long chefId,
                                Model model) {

        if (error != null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", error);
        }

//        if (chefId == null) {
//            return "redirect:/listChefs";
//        }
        if(chefId!=null){
            model.addAttribute("chefs",chefService.listChefs());
            model.addAttribute("chefId", chefId);
            model.addAttribute("chefName", chefService.findById(chefId).getFirstName());
        }
        model.addAttribute("Dishes", dishService.listDishes(chefId));
        return "/LabTemplates/listDishes";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id, @RequestParam(required = false) Long chefId) {
        dishService.delete(id);
        if(chefId!=null){
            return "redirect:/dishes?chefId=" + chefId;
        }else{
            return "redirect:/dishes";
        }
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam(required = false) Long chefId) {
        dishService.update(id, dishId, name, cuisine, preparationTime,chefId);
        if(chefId!=null){
            return "redirect:/dishes?chefId=" + chefId;
        }
        return "redirect:/dishes";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam(required = false) Long chefId) {
        dishService.create(dishId, name, cuisine, preparationTime,chefId);
        if(chefId!=null){
            return "redirect:/dishes?chefId=" + chefId;
        }

        return "redirect:/dishes";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model, @RequestParam(required = false) Long chefId) {
        if(chefId!=null){
            model.addAttribute("chefId", chefId);
        }
        model.addAttribute("chefs",chefService.listChefs());
        model.addAttribute("Dishes", dishService.listDishes(chefId));
        return "/LabTemplates/dish-form";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model, @RequestParam(required = false) Long chefId) {
        if (dishService.findById(id).isPresent()) {
            Dish dish = dishService.findById(id).get();
            if(chefId!=null){
                model.addAttribute("chefId", chefId);
            }
            model.addAttribute("chefs",chefService.listChefs());
            model.addAttribute("id", id);
            model.addAttribute("dishId", dish.getDishId());
            model.addAttribute("name", dish.getName());
            model.addAttribute("cuisine", dish.getCuisine());
            model.addAttribute("preparationTime", dish.getPreparationTime());
            return "LabTemplates/dish-form";
        }
        return "redirect:/dishes?error=DishNotFound";
    }

    @PostMapping
    public String displayDishPage(Model model, @RequestParam(required = false) Long chefId) {
        if (chefId != null) {
            return "redirect:/dishes?chefId=" + chefId;
        }

        model.addAttribute("Chefs", chefService.listChefs());
        model.addAttribute("error", true);
        model.addAttribute("errorMessage", "Please select a chef!");
        return "/LabTemplates/listChefs";
    }

    @PostMapping("/chefDetails")
    public String getChefDetails(Model model, @RequestParam(required = false) Long chefId) {
        if(chefId!=null) {
            Chef chef = chefService.findById(chefId);
            model.addAttribute("chefName", chef.getFirstName());
            model.addAttribute("chefBio", chef.getBio());
            model.addAttribute("chefDishes", dishService.listDishes(chefId));
        }
        return "/LabTemplates/chefDetails";
    }
}
