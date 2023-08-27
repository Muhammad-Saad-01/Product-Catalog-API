package net.muhammadsaad.rest.service.impl;

import com.querydsl.core.types.Predicate;
import net.muhammadsaad.rest.entity.Category;
import net.muhammadsaad.rest.exception.category.CategoryAlreadyExistException;
import net.muhammadsaad.rest.exception.category.CategoryNotFoundException;
import net.muhammadsaad.rest.mapper.CategoryMapper;
import net.muhammadsaad.rest.model.CategoryModel;
import net.muhammadsaad.rest.repository.CategoryRepository;
import net.muhammadsaad.rest.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public long createCategory(CategoryModel categoryModel) {
        if (categoryRepository.findCategoryByNameEquals(categoryModel.getName()).isPresent()) {
            throw new CategoryAlreadyExistException();
        }

        Category category = categoryMapper.toEntity(categoryModel);
        category = categoryRepository.save(category);
        return category.getId();
    }

    @Override
    public CategoryModel getCategoryById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        return categoryMapper.toModel(optionalCategory.get());
    }


    @Override
    public long updateCategory(long id, CategoryModel categoryModel) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        Category category = categoryMapper.updateCategory(categoryModel, optionalCategory.get());

        Category updatedCategory = categoryRepository.save(category);
        return updatedCategory.getId();
    }

    @Override
    public void deleteCategory(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        Category category = optionalCategory.get();
        category.setActive(false);
        categoryRepository.save(category);
    }

    @Override
    public void activateCategory(long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        Category category = optionalCategory.get();

        category.setActive(true);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryModel> getCategoriesByFilter(Predicate predicate) {
        Iterable<Category> categories = categoryRepository.findAll(predicate);

        return categoryMapper.toModels((List<Category>) categories);
    }

    @Override
    public long getCategoriesCount(Boolean active) {
        if (active == null)
            return categoryRepository.count();
        return categoryRepository.countAllByActiveEquals(active);
    }


}
