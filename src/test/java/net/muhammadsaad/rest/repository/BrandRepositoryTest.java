package net.muhammadsaad.rest.repository;

import net.muhammadsaad.rest.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static junit.runner.Version.id;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    // Test data
    private Brand brand1;
    private Brand brand2;
    private Brand brand3;

    private List<Brand> brands;

    @BeforeEach
    public void setUp() {
        brand1 = Brand.builder().id(1L)
                .name("Huawei")
                .imageUri("https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Huawei_logo_2018.svg/100px-Huawei_logo_2018.svg.png")
                .active(true)
                .about("Huawei Technologies Co., Ltd. is a Chinese multinational technology company headquartered in Shenzhen, Guangdong. " +
                        "It provides telecommunications equipment and sells consumer electronics, smartphones and is headquartered in Shenzhen, Guangdong.")
                .brandWebsiteUri("https://consumer.huawei.com/en/")
                .build();

        brand2 = Brand.builder().id(2L)
                .name("Samsung")
                .imageUri("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Samsung_Logo.svg/100px-Samsung_Logo.svg.png")
                .active(true)
                .about("Samsung is a South Korean multinational conglomerate headquartered in Samsung Town, Seoul. " +
                        "It comprises numerous affiliated businesses, most of them united under the Samsung brand, and is the largest South Korean chaebol.")
                .brandWebsiteUri("https://www.samsung.com/us/")
                .build();

        brand3 = Brand.builder().id(3L)
                .name("Apple")
                .imageUri("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/100px-Apple_logo_black.svg.png")
                .active(true)
                .about("Apple Inc. is an American multinational technology company headquartered in Cupertino, California, that designs, develops, and sells consumer electronics, computer software, and online services.")
                .brandWebsiteUri("https://www.apple.com/")
                .build();

        brands = new ArrayList<>();
        brands.add(brand1);
        brands.add(brand2);
        brands.add(brand3);


    }

    @Test
    void BrandRepository_SaveAll_shouldSaveAllBrands() {

        List<Brand> brandList = brandRepository.saveAll(brands);

        for (Brand brand: brandList) {
          assertThat(brand.getId()).isPositive();
        }

    }

    @Test
    void testFindAllBrands() {
        // arrange
        List<Brand> expectedBrands = new ArrayList<>();
        expectedBrands.add(brand1);
        expectedBrands.add(brand2);
        expectedBrands.add(brand3);

        // act
        brandRepository.saveAll(expectedBrands);

        List<Brand> actualBrands = brandRepository.findAll();

        // assert
        for(Brand brand: actualBrands) {
             for (Brand expectedBrand: expectedBrands) {
                 if (Objects.equals(brand.getName(), expectedBrand.getName())) {

                     assertEquals(brand.getImageUri(), expectedBrand.getImageUri());
                     assertEquals(brand.getAbout(), expectedBrand.getAbout());
                     assertEquals(brand.getBrandWebsiteUri(), expectedBrand.getBrandWebsiteUri());
                     assertEquals(brand.isActive(), expectedBrand.isActive());
                 }
             }
        }

    }

}