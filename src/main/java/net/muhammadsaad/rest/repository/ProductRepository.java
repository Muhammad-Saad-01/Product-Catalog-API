package net.muhammadsaad.rest.repository;

import net.muhammadsaad.rest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>
{

    @Query("SELECT p FROM Product p WHERE p.name = :productName")
    Optional<Product> findProductByNameEquals(@Param("productName") String productName);

    @Query("SELECT p FROM Product p WHERE p.productCode = :productCode")
    Optional<Product> findProductByProductCodeEquals(@Param("productCode") String productCode);

}
