package net.muhammadsaad.rest.service;

import net.muhammadsaad.rest.model.BrandModel;

import java.util.List;

public interface BrandService {

    long saveBrand(BrandModel brandModel);

    void updateBrand(long id, BrandModel brandModel);

    BrandModel getBrandById(long id);

    void deleteBrand(long id);

    void activateBrand(long id);

    List<BrandModel> getAllBrands();

}
