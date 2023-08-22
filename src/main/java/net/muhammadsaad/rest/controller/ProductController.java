package net.muhammadsaad.rest.controller;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import net.muhammadsaad.rest.entity.QProduct;
import net.muhammadsaad.rest.model.ProductModel;
import net.muhammadsaad.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")

public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public long createProduct(ProductModel productModel) {
        return productService.createProduct(productModel);
    }

    @GetMapping
    public List<ProductModel> getFilteredProducts(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String categoryName,
                                                  @RequestParam(required = false) String brandName,
                                                  @RequestParam(required = false) BigDecimal minPrice,
                                                  @RequestParam(required = false) BigDecimal maxPrice,
                                                  @RequestParam(required = false) BigDecimal exactPrice,
                                                  @RequestParam(required = false) Boolean active
    ) {

        Map<String, Object> filteringOptions = new HashMap<>();

        filteringOptions.put("name", name);
        filteringOptions.put("categoryName", categoryName);
        filteringOptions.put("brandName", brandName);
        filteringOptions.put("minPrice", minPrice);
        filteringOptions.put("maxPrice", maxPrice);
        filteringOptions.put("exactPrice", exactPrice);
        filteringOptions.put("active", active);

        return productService.getProductsByFilter(filteringOptions);
    }


    @GetMapping("/{productId}")
    public ProductModel getProductById(@PathVariable long productId) {
        return productService.getProductById(productId);
    }
    @GetMapping("/code/{productCode}")
    public ProductModel getProductById(@PathVariable String productCode) {
        return productService.getProductByProductCode(productCode);
    }

    @PutMapping("/{productId}")
    public void updateProduct(@PathVariable long productId, ProductModel productModel) {
        productService.updateProduct(productId, productModel);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping("/{productId}/activate")
    public void activateProduct(@PathVariable long productId) {
        productService.activateProduct(productId);
    }
}
