package mk.ukim.finki.wp.labvezba.web.controller;

import mk.ukim.finki.wp.labvezba.model.Dish;
import mk.ukim.finki.wp.labvezba.service.interfaces.ChefService;
import mk.ukim.finki.wp.labvezba.service.interfaces.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Controller
public class DishController {
    private final ChefService chefService;
    private final DishService dishService;

    public DishController(ChefService chefService, DishService dishService) {
        this.chefService = chefService;
        this.dishService = dishService;
    }
    @GetMapping("/dishes")
    public String getDishesPage(@RequestParam(required = false) String error, Model model,@RequestParam(required = false) Long chefId){
        if(error!=null){
            model.addAttribute("error",true);
            model.addAttribute("errorMsg",error);
        }
        if(chefId!=null) {
            model.addAttribute("dishes", dishService.listDishes());
            model.addAttribute("chefId", chefId);
            model.addAttribute("chefName", chefService.findById(chefId).getFirstName());

            return "listDishes";
        }
        return "redirect:/listChefs";
    }

    @PostMapping("/dishes")
    public String displayDishPage(@RequestParam(required = false) Long chefId) {
        if (chefId != null) {
            return "redirect:/dishes?chefId=" + chefId;
        }
        return "listChefs";
    }


    @GetMapping("/dishes/dish-form/{id}")
    public String getEditDishForm(@RequestParam Long chefId,@PathVariable Long id, Model model){
        model.addAttribute("chefId",chefId);
        model.addAttribute("dishId",id);
        model.addAttribute("chefs",chefService.listChefs());
        Dish foundDish = dishService.findById(id);
        model.addAttribute("foundDish",foundDish);
        return "dish-form";
    }


    @GetMapping("/dishes/dish-form")
    public String getAddDishPage(@RequestParam Long chefId,Model model){
        model.addAttribute("chefId",chefId);
        model.addAttribute("chefs",chefService.listChefs());
        return "dish-form";
    }

    @GetMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id,@RequestParam Long chefId){
        dishService.delete(id);
        return "redirect:/dishes?chefId="+chefId;
    }


    @PostMapping("/dishes/add")
    public String saveDish(@RequestParam Long dishChefId,@RequestParam Long chefId,@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime){
        dishService.create(dishId,name,cuisine,preparationTime,dishChefId);
        return "redirect:/dishes?chefId="+chefId;
    }

    @PostMapping("/dishes/edit/{id}")
    public String editDish(@RequestParam Long dishChefId,@RequestParam Long chefId,@PathVariable Long id,@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime){
        dishService.update(id,dishId,name,cuisine,preparationTime,dishChefId);
        return "redirect:/dishes?chefId="+chefId;
    }
}
