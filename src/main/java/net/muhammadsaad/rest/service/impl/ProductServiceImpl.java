package net.muhammadsaad.rest.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import lombok.AllArgsConstructor;
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
import net.muhammadsaad.rest.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;
    BrandRepository brandRepository;
    CategoryRepository categoryRepository;


    @Override
    public long createProduct(ProductModel productModel) {
        System.out.println(productModel);
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
    public ProductModel getProductByProductCode(String productCode) {
        return productMapper.toModel(productRepository.findProductByProductCodeEquals(productCode)
                .orElseThrow(ProductNotFoundException::new));
    }

    @Override
    public List<ProductModel> getProductsByFilter(Predicate predicate) {
        Iterable<Product> products = productRepository.findAll(predicate);
        return productMapper.toModels(products);
    }

    @Override
    public List<ProductModel> getProductsByFilter(Map<String, Object> filteringOptions) {
        QProduct product = QProduct.product;
        Predicate filterPredicate = getFilterPredicate(filteringOptions, product);
        Iterable<Product> products = productRepository.findAll(filterPredicate);
        return productMapper.toModels(products);
    }


    @Override
    public Page<ProductModel> getProducts(Map<String, Object> filteringOptions, Pageable pageable) {
        Predicate predicate = getFilterPredicate(filteringOptions, QProduct.product);

        return productRepository.findAll(predicate, pageable)
                .map(productMapper::toModel);
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

    private Predicate getFilterPredicate(Map<String, Object> filteringOptions, QProduct product) {
        BooleanBuilder predicate = new BooleanBuilder();

        filteringOptions.forEach((filterOption, filter) -> {
            if (filter != null) {
                switch (filterOption) {
                    case "name" -> predicate.and(product.name.eq((String) filter));

                    case "categoryName" -> // categoryName=Video Games,Electronics (comma separated)
                            handleIfThereOneOrMoreFilteringOptions((String) filter, product.category.name, predicate);

                    case "brandName" -> // brandName=EA Sports,Ubisoft (comma separated)
                            handleIfThereOneOrMoreFilteringOptions((String) filter, product.brand.name, predicate);

                    case "active" -> predicate.and(product.active.eq((Boolean) filter));

                    case "exactPrice" -> predicate.and(product.price.eq((BigDecimal) filter));

                    case "minPrice" -> predicate.and(product.price.goe((BigDecimal) filter));

                    case "maxPrice" -> predicate.and(product.price.loe((BigDecimal) filter));

                    default -> throw new IllegalStateException("Unexpected value: " + filterOption);
                }
            }
        });

        return predicate;
    }

    private void handleIfThereOneOrMoreFilteringOptions(String filter, StringPath filterOption, BooleanBuilder predicate) {

        if (filter.contains(",")) { // Multiple filter options (comma separated)
            List<String> filters = Arrays.asList(filter.split(","));
            BooleanBuilder filterPredicate = new BooleanBuilder();
            filters.forEach(filterItem -> filterPredicate.or(filterOption.eq(filterItem)));
            predicate.and(filterPredicate);
        } else {  // Single filter option
            predicate.and(filterOption.eq(filter));
        }
    }

}