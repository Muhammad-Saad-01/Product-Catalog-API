package net.muhammadsaad.rest.mapper;

import net.muhammadsaad.rest.entity.Category;
import net.muhammadsaad.rest.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public interface CategoryMapper {

    CategoryModel toModel(Category category);

    List<CategoryModel> toModels(List<Category> categories);

    Category toEntity(CategoryModel categoryModel);

    void updateCategoryFromModel(CategoryModel categoryModel, @MappingTarget Category category);
    @Mapping(target = "id", ignore = true)
    Category updateCategory(CategoryModel categoryModel, @MappingTarget Category category);
}
