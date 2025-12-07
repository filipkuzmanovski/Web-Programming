package mk.ukim.finki.wp2025.service.implementations;

import mk.ukim.finki.wp2025.model.Category;
import mk.ukim.finki.wp2025.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wp2025.service.categoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Categoryservice implements categoryService {
    //sekogash treba da e final za da se kreira samo ednash pri startup
    private final InMemoryCategoryRepository categoryRepository;

    //za da nemora nanovo da
    // ja inicijalizirame
    // categoriRepositorito tuku
    // da ja koristime taa  pri startuvanje
    // na aplikacijata mozheme
    // da ja injektirame
    //Ova se vika constructor dependency injection

    public Categoryservice(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category create(String name, String description) {
        if(name==null || description==null||name.isEmpty()||description.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category category=new Category(name,description);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category update(String name, String description) {
        if(name==null || description==null||name.isEmpty()||description.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category category=new Category(name,description);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void delete(String name) {
        if(name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String categoryName) {
        return categoryRepository.search(categoryName);
    }
}
