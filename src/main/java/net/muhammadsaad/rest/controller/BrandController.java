package net.muhammadsaad.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.muhammadsaad.rest.model.BrandModel;
import net.muhammadsaad.rest.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;


    @PostMapping()
    @Operation(summary = "Add a Brand")
    public long createBrand(BrandModel brandModel) {
        return brandService.saveBrand(brandModel);
    }

    @GetMapping("/{brandId}")
    @Operation(summary =  "Get a Brand by it's id")
    public BrandModel getBrandById(@PathVariable long brandId) {
        return brandService.getBrandById(brandId);
    }

    @GetMapping()
    @Operation(summary = "Get all the Brands")
    public List<BrandModel> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PutMapping("/{brandId}")
    @Operation(summary = "Update a Brand")
    public void updateBrand(@PathVariable long brandId, BrandModel brandModel) {
        brandService.updateBrand(brandId, brandModel);
    }

    @DeleteMapping("/{brandId}")
    @Operation(summary = "Delete a Brand by it's id(soft delete)")
    public void deleteBrand(@PathVariable long brandId) {
        brandService.deleteBrand(brandId);
    }

    @PutMapping("/{brandId}/activate")
    @Operation(summary = "Activate a Brand by it's id")
    public void activateBrand(@PathVariable long brandId) {
        brandService.activateBrand(brandId);
    }
}
