package net.muhammadsaad.rest.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import net.muhammadsaad.rest.entity.Brand;
import net.muhammadsaad.rest.entity.Category;
import net.muhammadsaad.rest.entity.Product;
import net.muhammadsaad.rest.exception.brand.BrandNotFoundException;
import net.muhammadsaad.rest.exception.category.CategoryNotFoundException;
import net.muhammadsaad.rest.exception.product.ProductAlreadyExistException;
import net.muhammadsaad.rest.exception.product.ProductNotFoundException;
import net.muhammadsaad.rest.mapper.ProductMapper;
import net.muhammadsaad.rest.model.ProductModel;
import net.muhammadsaad.rest.repository.BrandRepository;
import net.muhammadsaad.rest.repository.CategoryRepository;
import net.muhammadsaad.rest.repository.ProductRepository;
import net.muhammadsaad.rest.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;
    BrandRepository brandRepository;
    CategoryRepository categoryRepository;

    @Override
    public long addProduct(ProductModel productModel) {
        if (productRepository.findProductByNameEquals(productModel.getName()).isPresent())
            throw new ProductAlreadyExistException();
        Category category = categoryRepository.findCategoryByNameEquals(productModel.getCategoryName())
                .orElseThrow(CategoryNotFoundException::new);
        Brand brand = brandRepository.findBrandByNameEquals(productModel.getBrandName())
                .orElseThrow(BrandNotFoundException::new);
        Product product = productMapper.toEntity(productModel);
        product.setCategory(category);
        product.setBrand(brand);
        product = productRepository.save(product);
        return product.getId();
    }

    @Override
    public void updateProduct(Long productId, ProductModel productModel) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        categoryRepository.findCategoryByNameEquals(productModel.getCategoryName())
                .orElseThrow(CategoryNotFoundException::new);
        brandRepository.findBrandByNameEquals(productModel.getBrandName())
                .orElseThrow(BrandNotFoundException::new);
        product = productMapper.updateProduct(productModel, product);
        productRepository.save(product);

    }

    @Override
    public ProductModel getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        return productMapper.toModel(product);
    }

    @Override
    public List<ProductModel> getProductsByFilter(Predicate predicate) {
        Iterable<Product> products = productRepository.findAll(predicate);
        return productMapper.toModels(products);
    }

    @Override
    public void deleteProduct(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public BigDecimal getProductPrice(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        return product.getPrice();
    }

    @Override
    public void activateProduct(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        product.setActive(true);
        productRepository.save(product);
    }
}