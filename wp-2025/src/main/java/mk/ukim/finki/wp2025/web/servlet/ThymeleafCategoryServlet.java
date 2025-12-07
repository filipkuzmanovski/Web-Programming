package mk.ukim.finki.wp2025.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp2025.service.implementations.Categoryservice;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="Thymeleafprimer",urlPatterns = "/servlet/thymeleaf/category")
public class ThymeleafCategoryServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final Categoryservice categoryservice;
    public ThymeleafCategoryServlet(SpringTemplateEngine templateEngine, Categoryservice categoryservice) {
        this.templateEngine=templateEngine;
        this.categoryservice = categoryservice;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication((getServletContext())).buildExchange(req, resp);

        WebContext context=new WebContext(webExchange);
        context.setVariable("ipAdress",req.getRemoteAddr());
        context.setVariable("clientAgent",req.getHeader("User-Agent"));
        context.setVariable("Categories",this.categoryservice.listCategories());
        this.templateEngine.process("CategoryHTML.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName=req.getParameter("name");
        String categoryDescription=req.getParameter("desc");
        categoryservice.create(categoryName,categoryDescription);
        resp.sendRedirect("/servlet/thymeleaf/category");
    }
}
