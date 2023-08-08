package net.muhammadsaad.rest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @SequenceGenerator(
            name = "products_id_seq",
            sequenceName = "products_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id_seq")
    private long id;

    @Column(unique = true, nullable = false, length = 20)
    private String productCode;

    private int rating = 0;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;

    private String imageUri;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @PositiveOrZero
    private Long stock;

    private boolean active = true;

    private LocalDateTime createdAt ;

    private LocalDateTime updatedAt;
}
