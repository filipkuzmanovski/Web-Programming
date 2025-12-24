package mk.ukim.finki.wp.labvezba.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.labvezba.service.interfaces.ChefService;
import mk.ukim.finki.wp.labvezba.service.interfaces.DishService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="ChefListServlet",urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {
    private final ChefService chefService;
    private final DishService dishService;
    private final SpringTemplateEngine templateEngine;

    public ChefListServlet(ChefService chefService, DishService dishService, SpringTemplateEngine templateEngine) {
        this.chefService = chefService;
        this.dishService = dishService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication((getServletContext())).buildExchange(req,resp);
        resp.setContentType("text/html;charset=UTF-8");
        WebContext webContext=new WebContext(webExchange);
        webContext.setVariable("chefs",chefService.listChefs());
        templateEngine.process("listChefs.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefId=req.getParameter("chefId");
        if(chefId!=null){
            resp.sendRedirect("/dishes?chefId="+chefId);
        }else{
            resp.sendRedirect("/listChefs");
        }
    }
}
