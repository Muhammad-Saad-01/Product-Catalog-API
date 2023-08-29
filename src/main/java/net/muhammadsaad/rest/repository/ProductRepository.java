package net.muhammadsaad.rest.repository;

import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import net.muhammadsaad.rest.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository extends
        JpaRepository<Product, Long>,
        PagingAndSortingRepository<Product, Long>,
        QuerydslPredicateExecutor<Product> {

    @Query("SELECT p FROM Product p WHERE p.name = :productName")
    Optional<Product> findProductByNameEquals(@Param("productName") String productName);

    @Query("SELECT p FROM Product p WHERE p.productCode = :productCode")
    Optional<Product> findProductByProductCodeEquals(@Param("productCode") String productCode);

    @NonNull
    Page<Product> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);

    @Query(value = "SELECT c.name, COUNT(p) AS ProductCount " +
            "FROM Products p " +
            "JOIN Categories c " +
            "ON p.Category_Id = c.Id " +
            "GROUP BY c.name " +
            "ORDER BY ProductCount DESC"
            , nativeQuery = true)
    List<Object[]> countProductsByCategory();

    @Query(
            value = "SELECT b.name, COUNT(p) AS ProductCount " +
                    "FROM Products p " +
                    "JOIN Brands b " +
                    "ON p.Brand_Id = b.Id " +
                    "GROUP BY b.name " +
                    "ORDER BY ProductCount DESC"
            , nativeQuery = true
    )
    List<Object[]> countProductsByBrand();

    long countAllByActiveEquals(boolean b);
}
