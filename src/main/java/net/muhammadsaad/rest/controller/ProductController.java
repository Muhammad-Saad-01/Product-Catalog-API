package net.muhammadsaad.rest.controller;

import net.muhammadsaad.rest.model.ProductModel;
import net.muhammadsaad.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
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
                                                  @RequestParam(required = false, name = "category") String[] categories,
                                                  @RequestParam(required = false, name = "brand") String[] brands,
                                                  @RequestParam(required = false, name = "minPrice")BigDecimal minPrice,
                                                  @RequestParam(required = false, name = "maxPrice")BigDecimal maxPrice,
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


        Pageable pageable = Pageable
                .ofSize(Objects.requireNonNullElse(size, 9))
                .withPage(Objects.requireNonNullElse(page, 0));

        if (sort != null) {
            // sort = ["name,desc", "price,asc"]
            // sort[0] = "name,desc" -> sort[0] = "name" -> Sort.by("name") -> Sort.by("name").descending()
            // sort[1] = "price,asc" -> sort[1] = "price" -> Sort.by("price") -> Sort.by("price").ascending()
            Sort sortingOptions = Sort.unsorted();
            for (String sortOption : sort) {
                System.out.println(sortOption);
                String[] sortOptionParts = sortOption.split(",");
                if (sortOptionParts.length == 2) {
                    if (sortOptionParts[1].equalsIgnoreCase("desc")) {
                        sortingOptions = sortingOptions.and(Sort.by(sortOptionParts[0]).descending());
                    } else {
                        sortingOptions = sortingOptions.and(Sort.by(sortOptionParts[0]));
                    }
                } else {
                    sortingOptions = sortingOptions.and(Sort.by(sortOptionParts[0]));
                }

            }
            System.out.println(sortingOptions);
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortingOptions);
        }

        return productService.getProducts(filteringOptions, pageable);

    }

    @GetMapping
    public List<ProductModel> getAllProducts() {
        return productService.getAllProducts();
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
