package net.muhammadsaad.rest.repository;

import net.muhammadsaad.rest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, QuerydslPredicateExecutor<Category> {
    @Query("SELECT c FROM Category c WHERE c.name = :categoryName")
    Optional<Category> findCategoryByNameEquals(@Param("categoryName") String categoryName);


    boolean existsByNameEquals(String categoryName);
    @Query("SELECT count (c) FROM Category c WHERE c.active = :active")
    long countAllByActiveEquals(@Param("active") boolean active);
}
