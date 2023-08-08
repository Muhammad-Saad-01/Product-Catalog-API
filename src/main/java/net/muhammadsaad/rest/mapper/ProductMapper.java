package net.muhammadsaad.rest.mapper;

import net.muhammadsaad.rest.entity.Product;
import net.muhammadsaad.rest.model.ProductModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "brandName", source = "brand.name")
    ProductModel toModel(Product product);

    @InheritInverseConfiguration
    Product toEntity(ProductModel productModel);

    List<ProductModel> toModels(Iterable<Product> products);

    void updateProductFromModel(ProductModel productModel, @MappingTarget Product product);
    Product updateProduct(ProductModel productModel, @MappingTarget Product product);
}
