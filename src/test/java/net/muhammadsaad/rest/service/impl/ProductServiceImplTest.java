package net.muhammadsaad.rest.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import net.muhammadsaad.rest.entity.Brand;
import net.muhammadsaad.rest.entity.Category;
import net.muhammadsaad.rest.entity.Product;
import net.muhammadsaad.rest.entity.QProduct;
import net.muhammadsaad.rest.exception.brand.BrandNotFoundException;
import net.muhammadsaad.rest.exception.category.CategoryNotFoundException;
import net.muhammadsaad.rest.exception.product.ProductAlreadyExistException;
import net.muhammadsaad.rest.exception.product.ProductNotFoundException;
import net.muhammadsaad.rest.mapper.ProductMapper;
import net.muhammadsaad.rest.model.ProductModel;
import net.muhammadsaad.rest.repository.BrandRepository;
import net.muhammadsaad.rest.repository.CategoryRepository;
import net.muhammadsaad.rest.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, productMapper, brandRepository, categoryRepository);
    }

    @Test
    void addProduct_shouldReturnProductId_whenProductIsSuccessfullyAdded() {
        ProductModel productModel = ProductModel.builder().name("Product 1").build();
        Category category = Category.builder().name("Category 1").build();
        Brand brand = Brand.builder().name("Brand 1").build();
        when(productRepository.findProductByNameEquals(productModel.getName())).thenReturn(Optional.empty());
        when(categoryRepository.findCategoryByNameEquals(productModel.getCategoryName()))
                .thenReturn(Optional.of(category));
        when(brandRepository.findBrandByNameEquals(productModel.getBrandName()))
                .thenReturn(Optional.of(brand));
        when(productMapper.toEntity(productModel)).thenReturn(Product.builder().name("Product 1").build());
        when(productRepository.save(any())).thenReturn(Product.builder().id(1L).build());

        long id = productService.createProduct(productModel);

        assertThat(id).isEqualTo(1L);
    }

    @Test
    void addProduct_throwsProductAlreadyExistException_whenProductAlreadyExists() {
        ProductModel productModel = ProductModel.builder().name("Product 1").build();

        when(productRepository.findProductByNameEquals(productModel.getName())).thenReturn(Optional.of(Product.builder().name("Product 1").build()));

        assertThrows(ProductAlreadyExistException.class, () -> productService.createProduct(productModel));
    }
    @Test
    void addProduct_throwsCategoryNotFoundException_whenCategoryNotExists() throws ProductAlreadyExistException {
        ProductModel productModel = ProductModel.builder().name("Product 1").build();

        when(productRepository.findProductByNameEquals(any())).thenReturn(Optional.empty());
        when(categoryRepository.findCategoryByNameEquals(any())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> productService.createProduct(productModel));
    }

    @Test
    void addProduct_throwsBrandNotFoundException_whenBrandNotExists() {
        ProductModel productModel = ProductModel.builder().name("Product 1").build();
        Category category = Category.builder().name("Category 1").id(1L).build();

        when(productRepository.findProductByNameEquals(any())).thenReturn(Optional. empty());
        when(categoryRepository.findCategoryByNameEquals(any())).thenReturn(Optional.of(category));
        when(brandRepository.findBrandByNameEquals(any())).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> productService.createProduct(productModel));
    }

    @Test
    void updateProduct_shouldUpdateProduct_whenProductExists() {
        long productId = 1L;
        Product product = Product.builder().name("Product 1").id(productId).build();

        ProductModel productModel = ProductModel.builder().name("Product 1").id(productId).build();
        ProductModel updatedProductModel = ProductModel.builder().name("Product 2").id(productId).build();

        Product updatedProduct = Product.builder().name("Product 2").id(productId).build();
        Category category = Category.builder().name("Category 1").build();
        Brand brand = Brand.builder().name("Brand 1").build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(categoryRepository.findCategoryByNameEquals(productModel.getCategoryName()))
                .thenReturn(Optional.of(category));
        when(brandRepository.findBrandByNameEquals(productModel.getBrandName()))
                .thenReturn(Optional.of(brand));
        when(productMapper.updateProduct(any(),any())).thenReturn(updatedProduct);
        when(productRepository.save(any())).thenReturn(updatedProduct);

        productService.updateProduct(productId, productModel);

        assertThat(updatedProductModel.getName()).isEqualTo("Product 2");
    }

    @Test
    void updateProduct_throwsProductNotFoundException_whenProductDoesNotExist() {
        long productId = 1L;
        ProductModel productModel = ProductModel.builder().name("Product 1").build();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(productId, productModel));
    }

    @Test
    void getProductById_shouldReturnProduct_whenProductExists() throws ProductNotFoundException {
        long productId = 1L;
        Product product = Product.builder().name("Product 1").id(productId).build();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productMapper.toModel(any())).thenReturn(ProductModel.builder().name("Product 1").id(productId).build());

        ProductModel productModel = productService.getProductById(productId);

        assertThat(productModel.getName()).isEqualTo("Product 1");
    }

    @Test
    void getProductById_throwsProductNotFoundException_whenProductDoesNotExist() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(productId));
    }
    @Test
    void getProductByProductCode_shouldReturnProduct_whenProductExists() throws ProductNotFoundException {

        String productCode = "Product 1";
        Product product = Product.builder().name("Product 1").productCode(productCode).build();
        when(productRepository.findProductByProductCodeEquals(productCode)).thenReturn(Optional.of(product));
        when(productMapper.toModel(any())).thenReturn(ProductModel.builder().name("Product 1").productCode(productCode).build());

        ProductModel productModel = productService.getProductByProductCode(productCode);

        assertThat(productModel.getName()).isEqualTo("Product 1");
    }

    @Test
    void getProductByProductCode_throwsProductNotFoundException_whenProductDoesNotExist() {
        String productCode = "Product 1";
        when(productRepository.findProductByProductCodeEquals(productCode)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductByProductCode(productCode));
    }
    @Test
    void getProductsByFilter_shouldReturnProducts_whenProductsMatchFilter() {
        List<Product> products = List.of(
                Product.builder().name("Product 1").id(1L).build(),
                Product.builder().name("Product 2").id(2L).build(),
                Product.builder().name("Product 3").id(3L).build()
        );
        List<ProductModel> productModelList = List.of(
                ProductModel.builder().name("Product 1").id(1L).build(),
                ProductModel.builder().name("Product 2").id(2L).build(),
                ProductModel.builder().name("Product 3").id(3L).build()
        );
        when(productRepository.findAll((Predicate) any())).thenReturn(products);
        when(productMapper.toModels(any())).thenReturn(productModelList);
        BooleanBuilder predicate = new BooleanBuilder();
        QProduct qProduct = QProduct.product;
        predicate.and(qProduct.name.eq("Product 1"));
        Pageable pageable = Pageable.unpaged();
        Map<String, Object> filteringOptions = new HashMap<>();
        Page<ProductModel> productModels = productService.getProducts(filteringOptions, pageable);

        assertThat(productModels.getContent()).hasSize(3);
    }
    @Test
    void deleteProduct_shouldDeleteProduct_whenProductExists() throws ProductNotFoundException {
        long productId = 1L;
        Product product = Product.builder().name("Product 1").id(productId).build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);

        productService.deleteProduct(productId);

        assertThat(product.isActive()).isFalse();
    }

    @Test
    void deleteProduct_throwsProductNotFoundException_whenProductDoesNotExist() {
        long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(productId));
    }

    @Test
    void activateProduct_shouldActivateProduct_whenProductExists() throws ProductNotFoundException {
        long productId = 1L;
        Product product = Product.builder().name("Product 1").id(productId).build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);

        productService.activateProduct(productId);

        assertThat(product.isActive()).isTrue();
    }
    @Test
    void getProductPrice_shouldReturnProductPrice_whenProductExists() throws ProductNotFoundException {
        long productId = 1L;
        Product product = Product.builder().name("Product 1").id(productId).price(BigDecimal.TEN).build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        BigDecimal price = productService.getProductPrice(productId);

        assertThat(price).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void getProductPrice_throwsProductNotFoundException_whenProductDoesNotExist() {
        long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductPrice(productId));
    }
    @Test
    void activateProduct_throwsProductNotFoundException_whenProductDoesNotExist() {
        long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.activateProduct(productId));
    }
}