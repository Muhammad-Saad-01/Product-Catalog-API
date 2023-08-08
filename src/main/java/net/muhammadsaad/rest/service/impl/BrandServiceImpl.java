package net.muhammadsaad.rest.service.impl;

import lombok.AllArgsConstructor;
import net.muhammadsaad.rest.entity.Brand;
import net.muhammadsaad.rest.exception.brand.BrandAlreadyExistException;
import net.muhammadsaad.rest.exception.brand.BrandNotFoundException;
import net.muhammadsaad.rest.mapper.BrandMapper;
import net.muhammadsaad.rest.model.BrandModel;
import net.muhammadsaad.rest.repository.BrandRepository;
import net.muhammadsaad.rest.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

    @Override
    public long saveBrand(BrandModel brandModel) {
        if (brandRepository.findBrandByNameEquals(brandModel.getName()).isPresent())
            throw new BrandAlreadyExistException();
        Brand brand = brandMapper.toEntity(brandModel);
        brand = brandRepository.save(brand);
        return brand.getId();
    }

    @Override
    public void updateBrand(long id, BrandModel brandModel) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(BrandNotFoundException::new);

        brand = brandMapper.updateBrand(brandModel, brand);
        brandRepository.save(brand);
    }

    @Override
    public BrandModel getBrandById(long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(BrandNotFoundException::new);

        return brandMapper.toModel(brand);
    }

    @Override
    public void deleteBrand(long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(BrandNotFoundException::new);

        brand.setActive(false);
        brandRepository.save(brand);
    }

    @Override
    public void activateBrand(long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(BrandNotFoundException::new);

        brand.setActive(true);
        brandRepository.save(brand);
    }

    @Override
    public List<BrandModel> getAllBrands() {
        return brandMapper.toModels(brandRepository.findAll());
    }


}
