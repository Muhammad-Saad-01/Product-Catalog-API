package net.muhammadsaad.rest.service;

import com.querydsl.core.types.Predicate;
import net.muhammadsaad.rest.entity.Product;
import net.muhammadsaad.rest.model.ProductModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    long addProduct(ProductModel productModel);

    ProductModel getProductById(Long productId);

    List<ProductModel> getProductsByFilter(Predicate predicate);


    void updateProduct(Long productId, ProductModel productModel);

    void activateProduct(long productId);

    void deleteProduct(long productId);

    BigDecimal getProductPrice(long productId);
}
