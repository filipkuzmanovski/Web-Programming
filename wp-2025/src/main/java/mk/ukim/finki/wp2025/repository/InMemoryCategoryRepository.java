package mk.ukim.finki.wp2025.repository;

import mk.ukim.finki.wp2025.bootstrap.Dataholder;
import mk.ukim.finki.wp2025.model.Category;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

// za da mozhe da gi dobieme
// informaciite vnatre vo servletot
// mora preku
// in memory repositorium da gi dobieme

//bidejki e anotirano so repository
// se kreira
// vednash po startuvanje na aplikacijata
@Repository
public class InMemoryCategoryRepository {
    //obicno vo repositoriumi ke cuvame
    //metodi za azuriranje na daden
    // element dodavanje na daden element
    // i brishenje na element od repositorium

    public List<Category> findAll() {
        return Dataholder.categoryList;
    }

    public Category save(Category category) {
        if (category != null && category.getName() != null && !category.getName().isEmpty() && category.getDescription() != null && !category.getDescription().isEmpty()) {
            Dataholder.categoryList.removeIf(c->c.getName().equals(category.getName()));
            Dataholder.categoryList.add(category);
            return category;
        }else{
            return null;
        }
    }
    //ako ne najde kategorija po
    // ime ke vrati optional kojshto
    // sodrzi nishto za da se
    // spravuavme so exceptioni go
    // pravime ova
    public Optional<Category> findByName(String name) {
        return Dataholder.categoryList.stream().filter(c->c.getName().equals(name)).findFirst();
    }
    //ke ni gi vrati site kategorii i descripcii na kategorii bazirani na pushten text
    public List<Category> search(String text){
        return Dataholder.categoryList.stream().filter(c->c.getName().contains(text)||c.getDescription().contains(text)).toList();
    }
    public void deleteByName(String name) {
        if(name==null){
            return;
        }
        Dataholder.categoryList.removeIf(c->c.getName().equals(name));
    }
}
