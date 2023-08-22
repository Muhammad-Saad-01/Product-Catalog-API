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
    @Mapping(target = "inventoryStatus", source = "inventoryStatus")

    ProductModel toModel(Product product);

    @InheritInverseConfiguration
    Product toEntity(ProductModel productModel);

    List<ProductModel> toModels(Iterable<Product> products);

    Product updateProduct(ProductModel productModel, @MappingTarget Product product);
}
