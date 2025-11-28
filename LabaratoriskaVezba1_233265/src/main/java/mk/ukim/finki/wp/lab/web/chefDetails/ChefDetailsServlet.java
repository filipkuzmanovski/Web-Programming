package mk.ukim.finki.wp.lab.web.chefDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/servlet/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;

    public ChefDetailsServlet(SpringTemplateEngine templateEngine, ChefService chefService) {
        this.templateEngine = templateEngine;
        this.chefService = chefService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long chefId = Long.parseLong(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");

        chefService.addDishToChef(chefId, dishId);

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("chef", chefService.findById(chefId));
        context.setVariable("chefDishes", chefService.findById(chefId).getDishes());

        templateEngine.process("chefDetails.html", context, resp.getWriter());
    }
}
