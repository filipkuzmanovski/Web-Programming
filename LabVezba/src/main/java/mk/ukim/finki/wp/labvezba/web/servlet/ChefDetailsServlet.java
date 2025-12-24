package mk.ukim.finki.wp.labvezba.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.labvezba.model.Chef;
import mk.ukim.finki.wp.labvezba.model.Dish;
import mk.ukim.finki.wp.labvezba.service.interfaces.ChefService;
import mk.ukim.finki.wp.labvezba.service.interfaces.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChefDetailsServlet",urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    private final ChefService chefService;
    private final DishService dishService;
    private final SpringTemplateEngine templateEngine;

    public ChefDetailsServlet(ChefService chefService, DishService dishService, SpringTemplateEngine templateEngine) {
        this.chefService = chefService;
        this.dishService = dishService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication((getServletContext())).buildExchange(req,resp);
        resp.setContentType("text/html;charset=UTF-8");
        WebContext webContext=new WebContext(webExchange);
        chefService.addDishToChef(Long.valueOf(req.getParameter("chefId")), Long.valueOf(req.getParameter("dishId")));
        Chef chef=chefService.findById(Long.valueOf(req.getParameter("chefId")));
        webContext.setVariable("selectedChef",chef);
        List<Dish> dishes=chef.getDishes();
        webContext.setVariable("chefDishes",chef.getDishes());
        templateEngine.process("chefDetails.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("dishId")!=null&&!req.getParameter("dishId").isEmpty()){
            resp.sendRedirect("/chefDetails?chefId="+req.getParameter("chefId")+"&dishId="+req.getParameter("dishId"));
        } else {
            resp.sendRedirect("/dishes?chefId="+req.getParameter("chefId"));
        }
    }
}
