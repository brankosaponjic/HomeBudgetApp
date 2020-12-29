package brankosaponjic.homebudget.service;

import brankosaponjic.homebudget.model.Category;
import brankosaponjic.homebudget.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<Category> findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Category> createCategory(Category category) throws URISyntaxException {
        Category cat = categoryRepository.save(category);
        return ResponseEntity.created(new URI("/api/v1/category" + cat.getId())).body(cat);
    }

    public ResponseEntity<Category> updateCategory(Category category) throws URISyntaxException {
        Category cat = categoryRepository.save(category);
        return ResponseEntity.ok().body(cat);
    }

    public ResponseEntity<Category> deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
