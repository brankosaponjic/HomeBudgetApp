package brankosaponjic.homebudget.controller;

import brankosaponjic.homebudget.model.Category;
import brankosaponjic.homebudget.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/")
public class CategoryController {

    public final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public Collection<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        return categoryService.createCategory(category);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
