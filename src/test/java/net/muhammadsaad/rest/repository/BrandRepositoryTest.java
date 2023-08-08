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
//        brand1 = new Brand(
//                1L,
//                "LG",
//                "LG Corporation is a South Korean multinational conglomerate corporation " +
//                        "headquartered in Yeouido-dong, Seoul, South Korea. It is part of the LG Group, " +
//                        "comprising over 60 subsidiaries in electronics, chemical, and telecom fields.",
//                "https://www.lg.com/us/images/AboutLG/lg-logo-2x.png", true
//        );
//        brand2 = new Brand(
//                2L,
//                "Samsung",
//                "Samsung is a South Korean multinational conglomerate headquartered in Samsung Town, Seoul. " +
//                        "It comprises numerous affiliated businesses, most of them united under the Samsung brand, " +
//                        "and is the largest South Korean chaebol.",
//                "https://images.samsung.com/is/image/samsung/assets/us/about-us/brand/logo/mo/360_197_1.png?$FB_TYPE_B_PNG$",
//                false);
//        brand3 = new Brand(
//                3L,
//                "Apple",
//                "Apple Inc. is an American multinational technology company headquartered in Cupertino, California, " +
//                        "that designs, develops, and sells consumer electronics, computer software, and online services.",
//                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/100px-Apple_logo_black.svg.png",
//                true);

        brands = new ArrayList<>();
        brands.add(brand1);
        brands.add(brand2);
        brands.add(brand3);


    }

    @Test
    void BrandRepository_SaveAll_shouldSaveAllBrands() {
        List<Brand> brandList = brandRepository.saveAll(brands);


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
        assertEquals(expectedBrands, actualBrands);
    }

}