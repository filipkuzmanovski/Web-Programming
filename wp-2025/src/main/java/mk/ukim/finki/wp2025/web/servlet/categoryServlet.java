package mk.ukim.finki.wp2025.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp2025.model.Category;
import mk.ukim.finki.wp2025.service.implementations.Categoryservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//Ke kreirame strana za kategorii kadeshto
// vo doGet ke generirame strana kajshto
// ke se listaat site kategorii a so
// doPost metodot ke si stavame nekoja
// nova kategorija vnatre vo nashata
//stranica i taa nova kategorija da e zacuvana
// vo nekoja si lista od kategorii


//Vo ovoj del ke kreirame i klasata i site metodi vnatre vo eden servlet
// shto ne e vo red bidejki kodot ke bide mnogu nepregledliv
// i ke e teshko da se snajdeme vo nego podocna
// vo predmetot ke go popravime i ke stavime sekoj del vo poseben folder


@WebServlet(name="categoryServlet", urlPatterns = "/servlet/category")
class categoryServlet extends HttpServlet {

    private final Categoryservice categoryservice;

    public categoryServlet(Categoryservice categoryservice) {
        this.categoryservice = categoryservice;
    }

    //Htmlot poposle ke go refaktorirame
    // vo poseben dokument kaj template koristejki thymeleaf
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Ako sakame da gi ispechatime ip adresata i
        // browserot od kojshto bilo povikano baranjeto pravime vaka
        String ipAdress=req.getRemoteAddr();
        String clientAgent=req.getHeader("User-Agent");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Category Servlet</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Category Servlet</h1>");
        writer.println("<ul>");
        categoryservice.listCategories().forEach(category->writer.println("<li>"+category.getName()+ " (" + category.getDescription()+")</li>"));
        writer.println("</ul>");
        writer.println("<h3>");
        writer.println("IP adress of user is: "+ipAdress);
        writer.println("</h3>");
        writer.println("<h3>");
        writer.println("Client Agent of user is: "+clientAgent);
        writer.println("</h3>");
        writer.println("<h3>");
        writer.println("Add a category");
        writer.println("</h3>");
        //Koga pravime forma vo spring mora da stavime koj metod
        // ke go povikuva i potoa actionot odnosno urlto do kade
        // da gi prati podatocite ako e get gi prakja
        // preku url ako e post gi prakja preku bodyto odnosno
        // nekoe input pole
        writer.println("<form method='POST' action='/servlet/category'>");
        writer.println("<label for='name'>");
        writer.println("Name:");
        writer.println("</label>");
        //Za da mozhe da gi pratime podatocite
        // mora da gi imame atributite name i value bidejki vo body ke gi
        // imame inputsot a kluchevite ke ni se atributite value i name
        writer.println("<input id='name' type='text' name='name'/>");
        writer.println("</br>");
        writer.println("<label for='desc'>");
        writer.println("Description:");
        writer.println("</label>");
        writer.println("<input id='desc' type='text' name='desc'/>");
        writer.println("<input type='submit' value='Submit'/>");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category=req.getParameter("name");
        String description=req.getParameter("desc");
        categoryservice.create(category,description);
        resp.sendRedirect("/servlet/category");
    }
}
