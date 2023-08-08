package net.muhammadsaad.rest.repository;

import net.muhammadsaad.rest.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, QuerydslPredicateExecutor<Category> {
    Optional<Category> findCategoryByNameEquals(String categoryName);

}
