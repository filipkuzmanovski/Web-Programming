package mk.ukim.finki.wp.lab.web.Servlet.LabServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.LabExceptions.NotSelectedChefException;
import mk.ukim.finki.wp.lab.service.interfaces.DishService;
import mk.ukim.finki.wp.lab.service.interfaces.ChefService;
import mk.ukim.finki.wp.lab.service.interfaces.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {
    private final DishService dishService;
    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;
    public DishServlet(DishService dishService, SpringTemplateEngine templateEngine, ChefService chefService) {
        this.dishService = dishService;
        this.templateEngine = templateEngine;
        this.chefService = chefService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication((getServletContext())).buildExchange(req,resp);

        WebContext webContext=new WebContext(webExchange);
        Long chefId=Long.valueOf(req.getParameter("chefId"));
        String dishId=req.getParameter("dishId");
        fillInListDishesContext(webContext,chefId,dishId);

        templateEngine.process("/LabTemplates/dishesList.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("chefId")!=null&&!req.getParameter("chefId").isEmpty()){
            resp.sendRedirect("/dish?chefId=" + Long.valueOf(req.getParameter("chefId")));
        }else{
            IWebExchange webExchange= JakartaServletWebApplication.buildApplication((getServletContext())).buildExchange(req,resp);
            WebContext webContext = new WebContext(webExchange);
            fillInListChefsContext(webContext);
            templateEngine.process("/LabTemplates/listChefs.html",webContext,resp.getWriter());
        }
    }
    public void fillInListChefsContext(WebContext webContext){
        webContext.setVariable("Chefs",chefService.listChefs());
        webContext.setVariable("error",true);
        webContext.setVariable("errorMessage","Please select a chef!");
    }
    public void fillInListDishesContext(WebContext webContext,Long chefId,String dishId){

        webContext.setVariable("chefId",(chefId));
        webContext.setVariable("dishId",(dishId));
        webContext.setVariable("Dishes",dishService.listDishes(chefId));
        webContext.setVariable("chefName",chefService.findById(chefId).getFirstName());
    }
}
