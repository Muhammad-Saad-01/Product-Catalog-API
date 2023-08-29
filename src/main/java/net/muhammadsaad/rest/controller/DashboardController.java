package net.muhammadsaad.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.muhammadsaad.rest.service.BrandService;
import net.muhammadsaad.rest.service.CategoryService;
import net.muhammadsaad.rest.service.DashboardService;
import net.muhammadsaad.rest.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DashboardController {


    private final DashboardService dashboardService;

    @GetMapping("/products/count")
    @Operation(summary = "Get the total number of products  (all, active, inactive)")
    public long getProductsCount(@RequestParam(required = false) Boolean active) {
        return dashboardService.getProductsCount(active);
    }


    @GetMapping("/categories/count")
    @Operation(summary = "Get the total number of categories (all, active, inactive)")
    public long getCategoriesCount(@RequestParam(required = false) Boolean active) {
        return dashboardService.getCategoriesCount(active);
    }


    @GetMapping("/brands/count")
    @Operation(summary = "Get the total number of brands (all, active, inactive)")
    public long getBrandsCount(@RequestParam(required = false) Boolean active) {
        return dashboardService.getBrandsCount(active);
    }

    @GetMapping("/products/numberOfProductsByCategory")
    @Operation(summary = "Get the total number of products by category")
    public List<Map<String, Object>> getNumberOfProductsByCategory() {
        return dashboardService.getNumberOfProductsByCategory();
    }

    @GetMapping("/products/numberOfProductsByBrand")
    @Operation(summary = "Get the total number of products by brand")
    public List<Map<String, Object>> getNumberOfProductsByBrand() {
        return dashboardService.getNumberOfProductsByBrand();
    }

}
