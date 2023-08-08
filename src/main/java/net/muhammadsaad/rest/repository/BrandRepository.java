package net.muhammadsaad.rest.repository;

import net.muhammadsaad.rest.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("SELECT b FROM Brand b WHERE b.name = :brandName")
    Optional<Brand> findBrandByNameEquals(@Param("brandName") String brandName);


}
