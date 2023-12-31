package net.muhammadsaad.rest.controller;


import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.muhammadsaad.rest.model.ProductModel;
import net.muhammadsaad.rest.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;


import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private static final int DEFAULT_PAGE_SIZE = 9;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Add a product")
    public long createProduct(@Valid @RequestBody ProductModel productModel) {
        return productService.createProduct(productModel);
    }

    @GetMapping("/filter")
    @Operation(summary = "Get all the products in a paginated way with filtering and sorting options")
    public Page<ProductModel> getFilteredProducts(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false, name = "category") String[] categories,
                                                  @RequestParam(required = false, name = "brand") String[] brands,
                                                  @RequestParam(required = false, name = "minPrice") BigDecimal minPrice,
                                                  @RequestParam(required = false, name = "maxPrice") BigDecimal maxPrice,
                                                  @RequestParam(required = false, name = "price") BigDecimal exactPrice,
                                                  @RequestParam(required = false) Boolean active,
                                                  @RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer size,
                                                  @RequestParam(required = false) String[] sort
    ) {

        Map<String, Object> filteringOptions = new HashMap<>();

        filteringOptions.put("name", name);
        filteringOptions.put("categoryName", categories);
        filteringOptions.put("brandName", brands);
        filteringOptions.put("minPrice", minPrice);
        filteringOptions.put("maxPrice", maxPrice);
        filteringOptions.put("exactPrice", exactPrice);
        filteringOptions.put("active", active);


        int pageNumber = Objects.requireNonNullElse(page, DEFAULT_PAGE_NUMBER);
        int pageSize = Objects.requireNonNullElse(size, DEFAULT_PAGE_SIZE);
        Sort sortingOptions = parseSortingOption(sort);

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortingOptions);

        return productService.getProducts(filteringOptions, pageable);

    }

    @GetMapping
    @Operation(summary = "Get all the products")
    public List<ProductModel> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get a product by it's id")
    public ProductModel getProductById(@PathVariable long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/code/{productCode}")
    @Operation(summary = "Get a product by it's code")
    public ProductModel getProductById(@PathVariable String productCode) {
        return productService.getProductByProductCode(productCode);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update a product")
    public void updateProduct(@PathVariable long productId,@Valid @RequestBody  ProductModel productModel) {
        productService.updateProduct(productId, productModel);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product by it's id(soft delete)")
    public void deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping("/{productId}/activate")
    @Operation(summary = "Activate a product by it's id")
    public void activateProduct(@PathVariable long productId) {
        productService.activateProduct(productId);
    }

    private Sort parseSortingOption(String[] sort) {
        if (sort == null)
            return Sort.unsorted();

        if (sort.length == 2
                && (sort[1].equalsIgnoreCase("desc") || sort[1].equalsIgnoreCase("asc"))) {
            return sort[1].equalsIgnoreCase("desc") ?
                    Sort.by(sort[0]).descending() :
                    Sort.by(sort[0]);
        } // wrong parsing by spring

        return Arrays.stream(sort)

                .map(sortOption -> {
                    String[] sortOptionParts = sortOption.split(",");

                    if (sortOptionParts.length == 2) {

                        return sortOptionParts[1].equalsIgnoreCase("desc") ?
                                Sort.by(sortOptionParts[0]).descending() :
                                Sort.by(sortOptionParts[0]);
                    }
                    return Sort.by(sortOptionParts[0]);
                })
                .reduce(Sort.unsorted(), Sort::and);
    }
}
