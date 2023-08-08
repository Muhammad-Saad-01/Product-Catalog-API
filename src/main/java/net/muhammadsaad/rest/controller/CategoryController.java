package net.muhammadsaad.rest.controller;

import com.querydsl.core.BooleanBuilder;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import net.muhammadsaad.rest.entity.QCategory;
import net.muhammadsaad.rest.model.CategoryModel;
import net.muhammadsaad.rest.service.CategoryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Add a category")
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody @Valid CategoryModel categoryModel) {
        Long createdCategoryId = categoryService.createCategory(categoryModel);

        String uri = String.format("/categories/%s", createdCategoryId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(uri));

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a category")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id,
                                                 @RequestBody @Valid CategoryModel categoryModel) {
        Long updatedCategoryId = categoryService.updateCategory(id, categoryModel);

        String uri = String.format("/categories/%s", updatedCategoryId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(uri));

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @Operation(summary = "Get a category by it's id")
    @GetMapping("/{id}")
    public CategoryModel getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }


    @Operation(summary = "Get all the categories by their status or name (by default name is unique so, it will return only one category)")
    @GetMapping()
    public List<CategoryModel> getAllCategories(@RequestParam(required = false) Boolean active,
                                                @RequestParam(required = false) String name) {

        BooleanBuilder predicate = new BooleanBuilder();
        QCategory category = QCategory.category;

        if (active != null) {
            predicate.and(category.active.eq(active));
        }
        if (name != null) {
            predicate.and(category.name.eq(name));
        }

        return categoryService.getCategoriesByFilter(predicate);
    }

    @Operation(summary = "Delete a category by it's id (soft delete)")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}/activate")
    public void activateCategory(@PathVariable Long id) {
        categoryService.activateCategory(id);
    }
}
