package net.muhammadsaad.rest.service;

import com.querydsl.core.types.Predicate;
import net.muhammadsaad.rest.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    long createCategory(CategoryModel categoryModel);

    long updateCategory(long id, CategoryModel categoryModel);

    CategoryModel getCategoryById(long id);

    void deleteCategory(long id);

    void activateCategory(long categoryId);

    List<CategoryModel> getCategoriesByFilter(Predicate predicate);

    long getCategoriesCount(Boolean active);

}
