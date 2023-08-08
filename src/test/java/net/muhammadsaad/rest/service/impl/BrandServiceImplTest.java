package net.muhammadsaad.rest.service.impl;

import net.muhammadsaad.rest.entity.Brand;
import net.muhammadsaad.rest.exception.brand.BrandAlreadyExistException;
import net.muhammadsaad.rest.exception.brand.BrandNotFoundException;
import net.muhammadsaad.rest.mapper.BrandMapper;
import net.muhammadsaad.rest.model.BrandModel;
import net.muhammadsaad.rest.repository.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class BrandServiceImplTest {


    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    private BrandServiceImpl brandService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        brandService = new BrandServiceImpl(brandRepository, brandMapper);
    }

    @Test
    void saveBrand_shouldThrowBrandAlreadyExistException_whenBrandWithGivenNameAlreadyExists() {
        BrandModel brandModel = new BrandModel();
        brandModel.setName("Apple");

        when(brandRepository.findBrandByNameEquals(brandModel.getName())).thenReturn(Optional.of(new Brand()));

        assertThrows(BrandAlreadyExistException.class, () -> brandService.saveBrand(brandModel));
    }

    @Test
    void saveBrand_shouldReturnBrandId_whenBrandIsSuccessfullySaved() {
        BrandModel brandModel = new BrandModel();
        brandModel.setName("Apple");
        Brand brand = new Brand();

        brand.setName("Apple");


        when(brandRepository.findBrandByNameEquals(brandModel.getName())).thenReturn(Optional.empty());
        when(brandMapper.toEntity(brandModel)).thenReturn(brand);
        brand.setId(1L);
        when(brandRepository.save(Mockito.any(Brand.class))).thenReturn(brand);

        long brandId = brandService.saveBrand(brandModel);

        Assertions.assertEquals(1L, brandId);
    }

    @Test
    void updateBrand_shouldThrowBrandNotFoundException_whenBrandWithGivenIdDoesNotExist() {
        long id = 1L;
        BrandModel brandModel = new BrandModel();
        brandModel.setName("Apple");

        when(brandRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> brandService.updateBrand(id, brandModel))
                .isInstanceOf(BrandNotFoundException.class)
                .hasMessageContaining("Brand not found");


    }

    @Test
    void updateBrand_shouldUpdateBrand_whenBrandWithGivenIdExists() {
        long id = 1L;
        BrandModel brandModel = new BrandModel();
        brandModel.setName("Apple");
        Brand brand1 = new Brand();
        brand1.setId(id);
        brand1.setName("Samsung");


        Brand brand = new Brand();
        brand.setId(id);
        brand.setName("Apple");

        when(brandRepository.findById(id)).thenReturn(Optional.of(brand1));
//        Mockito.when(brandMapper.updateBrand(brandModel, brand)).thenReturn(brand);
        when(brandRepository.save(brand1)).thenReturn(brand);

        brandService.updateBrand(id, brandModel);

        assertThat(brand.getName()).isEqualTo("Apple");
        assertThat(brand.getId()).isEqualTo(1L);

    }

    @Test
    void getBrandById_shouldThrowBrandNotFoundException_whenBrandWithGivenIdDoesNotExist() {
        long id = 1L;

        when(brandRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> brandService.getBrandById(id)).isInstanceOf(BrandNotFoundException.class)
                .hasMessageContaining("Brand not found");
    }

    @Test
    void getBrandById_shouldReturnBrandModel_whenBrandWithGivenIdExists() {
        long id = 1L;
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName("Apple");

        BrandModel expectedBrandModel = new BrandModel();
        expectedBrandModel.setId(id);
        expectedBrandModel.setName("Apple");


        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        when(brandMapper.toModel(brand)).thenReturn(expectedBrandModel);

        BrandModel brandModel = brandService.getBrandById(id);

        Assertions.assertEquals("Apple", brandModel.getName());
    }


    @Test
    void testDeleteBrand() {
        long id = 1L;
        Brand brand = new Brand();
        brand.setName("Nike");
        brand.setId(id);
        brand.setActive(true);
        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));

        brandService.deleteBrand(id);
        assertThat(brand.isActive()).isFalse();

    }

    @Test
    void testActivateBrand() {
        long id = 1L;
        Brand brand = new Brand();
        brand.setName("Nike");
        brand.setId(id);
        brand.setActive(false);
        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));

        brandService.activateBrand(id);

        assertThat(brand.isActive()).isTrue();
    }

    @Test
    void testGetAllBrands() {
        Brand brand1 = Brand.builder().name("Nike").build();
        Brand brand2 = Brand.builder().name("Adidas").build();
        Brand brand3 = Brand.builder().name("Puma").build();
        List<Brand> brands = List.of(brand1, brand2, brand3);
        when(brandRepository.findAll()).thenReturn(brands);
        when(brandMapper.toModels(brands)).thenReturn(List.of(
                BrandModel.builder().name("Nike").id(1L).build(),
                BrandModel.builder().name("Adidas").id(2L).build(),
                BrandModel.builder().name("Puma").id(3L).build()
        ));

        List<BrandModel> brandModels = brandService.getAllBrands();

        assertThat(brandModels).hasSize(3);
        assertThat(brandModels.get(0).getName()).isEqualTo("Nike");
        assertThat(brandModels.get(1).getName()).isEqualTo("Adidas");
        assertThat(brandModels.get(2).getName()).isEqualTo("Puma");
    }
}