package net.muhammadsaad.rest.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import net.muhammadsaad.rest.entity.Category;
import net.muhammadsaad.rest.entity.QCategory;
import net.muhammadsaad.rest.exception.category.CategoryAlreadyExistException;
import net.muhammadsaad.rest.exception.category.CategoryNotFoundException;
import net.muhammadsaad.rest.mapper.CategoryMapper;
import net.muhammadsaad.rest.model.CategoryModel;
import net.muhammadsaad.rest.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
    }

    @Test
    void saveCategory_shouldReturnCategoryId_whenCategoryIsSuccessfullySaved() {
        CategoryModel categoryModel = CategoryModel.builder().name("Electronics").build();
        when(categoryRepository.findCategoryByNameEquals(categoryModel.getName())).thenReturn(Optional.empty());
        when(categoryMapper.toEntity(categoryModel)).thenReturn(Category.builder().name("Electronics").build());
        when(categoryRepository.save(any())).thenReturn(Category.builder().id(1L).build());

        long id = categoryService.createCategory(categoryModel);

        assertThat(id).isEqualTo(1L);
    }

    @Test
    void saveCategory_throwsCategoryAlreadyExistException_whenCategoryAlreadyExists() {
        CategoryModel categoryModel = CategoryModel.builder().name("Electronics").build();
        when(categoryRepository.findCategoryByNameEquals(categoryModel.getName())).thenReturn(Optional.of(Category.builder().name("Electronics").build()));

        assertThrows(CategoryAlreadyExistException.class, () -> categoryService.createCategory(categoryModel));
    }

    @Test
    void getCategoryById_shouldReturnCategory_whenCategoryExists() {
        long id = 1L;
        Category category = Category.builder().name("Electronics").id(id).build();

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryMapper.toModel(category)).thenReturn(CategoryModel.builder().name("Electronics").id(id).build());
        CategoryModel categoryModel = categoryService.getCategoryById(id);

        assertThat(categoryModel.getName()).isEqualTo("Electronics");

    }
    @Test
    void getCategoryById_throwsCategoryNotFoundException_whenCategoryDoesNotExist() {
        long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryById(id));
    }
    @Test
    void updateCategory_shouldUpdateCategory_whenCategoryExists() {
        long id = 1L;
        CategoryModel categoryModel = CategoryModel.builder().name("Electronics").build();
        Category category = Category.builder().name("Electronics").build();
        category.setId(id);
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryMapper.updateCategory(categoryModel, category)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);

        categoryService.updateCategory(id, categoryModel);

        assertThat(category.getName()).isEqualTo("Electronics");
    }
    @Test
    void updateCategory_throwsCategoryNotFoundException_whenCategoryDoesNotExist() {
        long id = 1L;
        CategoryModel categoryModel = CategoryModel.builder().name("Electronics").build();
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(id, categoryModel));
    }

    @Test
    void deleteCategory_shouldDeleteCategory_whenCategoryExists() {
        long id = 1L;
        Category category = Category.builder().name("Electronics").id(1L).build();
        category.setId(id);
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        categoryService.deleteCategory(id);

        assertThat(category.isActive()).isFalse();
    }
    @Test
    void deleteCategory_throwsCategoryNotFoundException_whenCategoryDoesNotExist() {
        long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory(id));
    }

    @Test
    void activateCategory_shouldActivateCategory_whenCategoryExists() {
        long id = 1L;
        Category category = Category.builder().name("Electronics").id(1L).build();

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        categoryService.activateCategory(id);
        assertThat(category.isActive()).isTrue();

    }


    @Test
    void activateCategory_throwsCategoryNotFoundException_whenCategoryDoesNotExist() {
        long id = 1L;

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.activateCategory(id));
    }
    @Test
    void getCategoriesByFilter_shouldReturnCategories_whenCategoriesMatchFilter() {
        List<Category> categories = List.of(
                Category.builder().name("Electronics").id(1L).build(),
                Category.builder().name("Appliances").id(2L).build(),
                Category.builder().name("Shoes").id(3L).build()
        );

        List<CategoryModel> categoryModelList = List.of(
                CategoryModel.builder().name("Electronics").id(1L).build(),
                CategoryModel.builder().name("Appliances").id(2L).build(),
                CategoryModel.builder().name("Shoes").id(3L).build()
        );
        when(categoryRepository.findAll((Predicate) any())).thenReturn(categories);
        when(categoryMapper.toModels(any())).thenReturn(categoryModelList);
        BooleanBuilder predicate = new BooleanBuilder();
        QCategory category = QCategory.category;
        predicate.and(category.name.eq("Electronics"));
        List<CategoryModel> categoryModels = categoryService.getCategoriesByFilter(predicate);

        assertThat(categoryModels.get(0).getName()).isEqualTo("Electronics");
    }
}
