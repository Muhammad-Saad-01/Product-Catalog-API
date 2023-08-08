package net.muhammadsaad.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.muhammadsaad.rest.model.BrandModel;
import net.muhammadsaad.rest.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@CrossOrigin(origins = "http://localhost:4200")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping()
    @Operation(summary = "Add a Brand")
    public long createBrand(BrandModel brandModel) {
        return brandService.saveBrand(brandModel);
    }

    @GetMapping("/{brandId}")
    public BrandModel getBrandById(@PathVariable long brandId) {
        return brandService.getBrandById(brandId);
    }

    @GetMapping()
    public List<BrandModel> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PutMapping("/{brandId}")
    public void updateBrand(@PathVariable long brandId, BrandModel brandModel) {
        brandService.updateBrand(brandId, brandModel);
    }

    @DeleteMapping("/{brandId}")
    public void deleteBrand(@PathVariable long brandId) {
        brandService.deleteBrand(brandId);
    }

    @PutMapping("/{brandId}/activate")
    public void activateBrand(@PathVariable long brandId) {
        brandService.activateBrand(brandId);
    }
}
