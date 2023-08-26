package net.muhammadsaad.rest.controller;

import com.querydsl.core.types.Predicate;
import net.muhammadsaad.rest.entity.Product;
import net.muhammadsaad.rest.model.ProductModel;
import net.muhammadsaad.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


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

    @GetMapping("/filter")
    public Page<ProductModel> getFilteredProducts(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String categoryName,
                                                  @RequestParam(required = false) String brandName,
                                                  @RequestParam(required = false) BigDecimal minPrice,
                                                  @RequestParam(required = false) BigDecimal maxPrice,
                                                  @RequestParam(required = false) BigDecimal exactPrice,
                                                  @RequestParam(required = false) Boolean active,
                                                  @RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer size
    ) {

        Map<String, Object> filteringOptions = new HashMap<>();

        filteringOptions.put("name", name);
        filteringOptions.put("categoryName", categoryName);
        filteringOptions.put("brandName", brandName);
        filteringOptions.put("minPrice", minPrice);
        filteringOptions.put("maxPrice", maxPrice);
        filteringOptions.put("exactPrice", exactPrice);
        filteringOptions.put("active", active);


        Pageable pageable = Pageable
                .ofSize(Objects.requireNonNullElse(size, 12))
                .withPage(Objects.requireNonNullElse(page, 0));
        

        return  productService.getProducts(filteringOptions, pageable);

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
