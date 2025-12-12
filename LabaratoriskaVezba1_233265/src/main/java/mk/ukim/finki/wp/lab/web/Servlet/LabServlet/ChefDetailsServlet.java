//package mk.ukim.finki.wp.lab.web.Servlet.LabServlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.Chef;
//import mk.ukim.finki.wp.lab.model.Dish;
//import mk.ukim.finki.wp.lab.service.interfaces.ChefService;
//import mk.ukim.finki.wp.lab.service.interfaces.DishService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name="ChefDetailsServlet",urlPatterns = "/chefDetails")
//public class ChefDetailsServlet extends HttpServlet {
//    SpringTemplateEngine templateEngine=new SpringTemplateEngine();
//    ChefService chefService;
//    DishService dishService;
//    ChefDetailsServlet(ChefService chefService,SpringTemplateEngine templateEngine,DishService dishService) {
//        this.chefService=chefService;
//        this.templateEngine=templateEngine;
//        this.dishService=dishService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange= JakartaServletWebApplication.buildApplication((getServletContext())).buildExchange(req,resp);
//        WebContext webContext=new WebContext(webExchange);
//        Long chefId=Long.parseLong(req.getParameter("chefId"));
//        String dishId=req.getParameter("dishId");
//        fillChefDetailsPageContext(webContext,chefId,dishId);
//        templateEngine.process("LabTemplates/chefDetails.html",webContext,resp.getWriter());
//        return;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Dish dish=dishService.findByDishId(req.getParameter("dishId"));
//        Chef chef=chefService.findById(Long.valueOf(req.getParameter("chefId")));
//        IWebExchange webExchange= JakartaServletWebApplication.buildApplication((getServletContext())).buildExchange(req,resp);
//        WebContext webContext=new WebContext(webExchange);
//        //ke stavime ako ne e odbran dish da ni se pojavi error message na stranicata
//        if(dish==null){
//            Long chefId=Long.valueOf(req.getParameter("chefId"));
//            boolean typeOfProblem=true;
//            String dishId=req.getParameter("dishId");
//            fillDishesPageContext(webContext,chefId,typeOfProblem,dishId);
//
//            templateEngine.process("/LabTemplates/listDishes.html",webContext,resp.getWriter());
//            return;
//        }
//        if(!chef.getDishes().contains(dish)&&dish!=null){
//            chef.getDishes().add(dish);
//        }else{
//            Long chefId=Long.valueOf(req.getParameter("chefId"));
//            String dishId=req.getParameter("dishId");
//            boolean typeOfProblem=false;
//            fillDishesPageContext(webContext,chefId,typeOfProblem,dishId);
//
//            templateEngine.process("/LabTemplates/listDishes.html",webContext,resp.getWriter());
//            return;
//        }
//
//        resp.sendRedirect("/chefDetails?chefId="+chef.getId() +"&dishId="+dish.getDishId());
//
//    }
//    private void fillDishesPageContext(WebContext webContext, Long chefId,boolean typeOfProblem,String dishId) {
//        Chef chef = chefService.findById(chefId);
//
//        webContext.setVariable("chefId", chefId);
//        webContext.setVariable("dishId", dishId);
//        webContext.setVariable("Dishes", dishService.listDishes());
//        webContext.setVariable("chefName", chef.getFirstName());
//        webContext.setVariable("error",true);
//        if(typeOfProblem){
//            webContext.setVariable("errorMessage","Please select a dish!");
//        }else{
//            webContext.setVariable("errorMessage","Please select a dish that you haven't selected yet!");
//        }
//    }
//    private void fillChefDetailsPageContext(WebContext webContext, Long chefId,String dishId) {
//        webContext.setVariable("chefId",(chefId));
//        webContext.setVariable("dishId",(dishId));
//        webContext.setVariable("chefDishes",chefService.findById(chefId).getDishes());
//        webContext.setVariable("chefName",chefService.findById(chefId).getFirstName());
//        webContext.setVariable("chefBio",chefService.findById(chefId).getBio());
//    }
//}
