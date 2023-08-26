package net.muhammadsaad.rest.service;

import com.querydsl.core.types.Predicate;
import net.muhammadsaad.rest.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    long createProduct(ProductModel productModel);

    ProductModel getProductById(Long productId);

    ProductModel getProductByProductCode(String productCode);

    List<ProductModel> getProductsByFilter(Predicate predicate);

    List<ProductModel> getProductsByFilter(Map<String, Object> filteringOptions);


    void updateProduct(Long productId, ProductModel productModel);

    void activateProduct(long productId);

    Page<ProductModel> getProducts(Map<String, Object> filteringOptions, Pageable pageable);

    void deleteProduct(long productId);

    BigDecimal getProductPrice(long productId);

}
