//package mk.ukim.finki.wp.labvezba.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.labvezba.service.interfaces.ChefService;
//import mk.ukim.finki.wp.labvezba.service.interfaces.DishService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name="DishServlet",urlPatterns = "/dish")
//public class DishServlet extends HttpServlet {
//    private final ChefService chefService;
//    private final DishService dishService;
//    private final SpringTemplateEngine templateEngine;
//
//    public DishServlet(ChefService chefService, DishService dishService, SpringTemplateEngine templateEngine) {
//        this.chefService = chefService;
//        this.dishService = dishService;
//        this.templateEngine = templateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange= JakartaServletWebApplication.buildApplication((getServletContext())).buildExchange(req,resp);
//
//        WebContext webContext=new WebContext(webExchange);
//        webContext.setVariable("dishes",dishService.listDishes());
//        webContext.setVariable("chefId",req.getParameter("chefId"));
//        webContext.setVariable("chefName",chefService.findById(Long.valueOf(req.getParameter("chefId"))).getFirstName());
//        templateEngine.process("dishesList.html",webContext,resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(req.getParameter("chefId")!=null&&!req.getParameter("chefId").isEmpty()){
//            resp.sendRedirect("/dish?chefId="+req.getParameter("chefId"));
//        }else{
//            resp.sendRedirect("/listChefs");
//        }
//    }
//}
