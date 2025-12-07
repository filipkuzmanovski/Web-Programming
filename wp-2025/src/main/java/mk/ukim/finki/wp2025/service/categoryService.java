package mk.ukim.finki.wp2025.service;

import mk.ukim.finki.wp2025.model.Category;

import java.util.List;

public interface categoryService {
    Category create(String name, String description);

    Category update(String name,String description);

    void delete(String name);

    List<Category> listCategories();

    List<Category> searchCategories(String categoryName);
}
