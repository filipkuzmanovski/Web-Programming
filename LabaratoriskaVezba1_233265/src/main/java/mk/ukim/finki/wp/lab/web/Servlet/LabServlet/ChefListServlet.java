package mk.ukim.finki.wp.lab.web.Servlet.LabServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.interfaces.ChefService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="ChefListServlet", urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;

    ChefListServlet(ChefService chefService,SpringTemplateEngine templateEngine) {
        this.chefService = chefService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication((getServletContext())).buildExchange(req,resp);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("Chefs",chefService.listChefs());
        templateEngine.process("/LabTemplates/listChefs.html",webContext,resp.getWriter());
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefId=req.getParameter("chefId");
        //Proveri go ova
        if(chefId!=null&&!chefId.isEmpty()){
            resp.sendRedirect("/dish?chefId="+chefId);
        }else{
            resp.sendRedirect("/listChefs");
        }
    }
}
