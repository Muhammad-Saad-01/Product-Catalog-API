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

    private static void handleIfThereOneOrMoreFilteringOptions(String filter, StringPath filterOption, BooleanBuilder predicate) {

        if (filter.contains(",")) { // Multiple filter options (comma separated)
            List<String> filters = Arrays.asList(filter.split(","));
            BooleanBuilder filterPredicate = new BooleanBuilder();
            filters.forEach(filterItem -> filterPredicate.or(filterOption.eq(filterItem)));
            predicate.and(filterPredicate);
        } else {  // Single filter option
            predicate.and(filterOption.eq(filter));
        }
    }

    @PostMapping
    public long createProduct(ProductModel productModel) {
        return productService.addProduct(productModel);
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


        QProduct product = QProduct.product; // QueryDSL QClass for Product Entity


        Map<String, Object> filteringOptions = new HashMap<>();

        filteringOptions.put("name", name);
        filteringOptions.put("categoryName", categoryName);
        filteringOptions.put("brandName", brandName);
        filteringOptions.put("minPrice", minPrice);
        filteringOptions.put("maxPrice", maxPrice);
        filteringOptions.put("exactPrice", exactPrice);
        filteringOptions.put("active", active);


        Predicate filterPredicate = getFilterPredicate(filteringOptions, product);


        return productService.getProductsByFilter(filterPredicate);
    }

    private Predicate getFilterPredicate(Map<String, Object> filteringOptions, QProduct product) {
        BooleanBuilder predicate = new BooleanBuilder();

        filteringOptions.forEach((filterOption, filter) -> {
            if (filter != null) {
                switch (filterOption) {
                    case "name" -> predicate.and(product.name.eq((String) filter));

                    case "categoryName" ->
                            handleIfThereOneOrMoreFilteringOptions((String) filter, product.category.name, predicate);

                    case "brandName" ->
                            handleIfThereOneOrMoreFilteringOptions((String) filter, product.brand.name, predicate);

                    case "active" -> predicate.and(product.active.eq((Boolean) filter));

                    case "exactPrice" -> predicate.and(product.price.eq((BigDecimal) filter));

                    case "minPrice" -> predicate.and(product.price.goe((BigDecimal) filter));

                    case "maxPrice" -> predicate.and(product.price.loe((BigDecimal) filter));

                    default -> throw new IllegalStateException("Unexpected value: " + filterOption);
                }
            }
        });

        return predicate;
    }

    @GetMapping("/{productId}")
    public ProductModel getProductById(@PathVariable long productId) {
        return productService.getProductById(productId);
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
