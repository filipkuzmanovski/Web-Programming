package mk.ukim.finki.wp.lab.web.chefDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    private final ChefService chefService;

    public ChefDetailsServlet(ChefService chefService) {
        this.chefService = chefService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long chefId = Long.parseLong(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");

        chefService.addDishToChef(chefId, dishId);

        Chef chef = chefService.findById(chefId);
        List<Dish> chefDishes = chef.getDishes();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>Chef Details</title></head><body>");
        out.printf("<h1>Chef: %s %s</h1>", chef.getFirstName(), chef.getLastName());
        out.printf("<h2>Bio: %s</h2>", chef.getBio());
        out.println("<h2>Dishes prepared by this chef:</h2>");
        out.println("<ul>");
        for (Dish dish : chefDishes) {
            out.printf("<li>%s (%s, %d min)</li>", dish.getName(), dish.getCuisine(), dish.getPreperationTime());
        }
        out.println("</ul>");
        out.println("</body></html>");
    }
}

