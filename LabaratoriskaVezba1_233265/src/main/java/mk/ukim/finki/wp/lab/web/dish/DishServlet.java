package mk.ukim.finki.wp.lab.web.dish;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    private final DishService dishService;
    private final ChefService chefService;

    public DishServlet(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String chefIdStr = req.getParameter("chefId");
        if (chefIdStr == null || chefIdStr.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Chef ID is missing");
            return;
        }

        Long chefId = Long.parseLong(chefIdStr);
        Chef chef = chefService.findById(chefId);
        List<Dish> allDishes = dishService.listDishes();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>Add Dish to Chef</title></head><body>");
        out.printf("<h1>Select the Dish to add to a chef %s %s</h1>", chef.getFirstName(), chef.getLastName());

        out.println("<form action='/chefDetails' method='POST'>");
        for (Dish dish : allDishes) {
            out.printf("<input type='radio' name='dishId' value='%s'>%s<br/>", dish.getDishId(), dish.getName());
        }

        out.printf("<input type='hidden' name='chefId' value='%d'>", chefId);
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");

        out.println("</body></html>");
    }
}

