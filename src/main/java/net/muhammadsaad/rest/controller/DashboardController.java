package net.muhammadsaad.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.muhammadsaad.rest.service.BrandService;
import net.muhammadsaad.rest.service.CategoryService;
import net.muhammadsaad.rest.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DashboardController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @GetMapping("/products/count")
    @Operation(summary = "Get the total number of products  (all, active, inactive)")
    public long getProductsCount(@RequestParam(required = false) Boolean active) {
        return productService.getProductsCount(active);
    }


    @GetMapping("/categories/count")
    @Operation(summary = "Get the total number of categories (all, active, inactive)")
    public long getCategoriesCount(@RequestParam(required = false) Boolean active) {
        return categoryService.getCategoriesCount(active);
    }


    @GetMapping("/brands/count")
    @Operation(summary = "Get the total number of brands (all, active, inactive)")
    public long getBrandsCount(@RequestParam(required = false) Boolean active) {
        return brandService.getBrandsCount(active);
    }

}
