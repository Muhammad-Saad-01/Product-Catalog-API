package net.muhammadsaad.rest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be at most 255 characters")
    @Schema(type = "string", example = "FC 24")
    private String name;

    @NotBlank(message = "Product code is required")
    @Size(max = 20, message = "Product code must be at most 20 characters")

    @Schema(type = "string", example = "P-FC24")
    private String productCode;

    @Size(max = 255, message = "Description must be at most 255 characters")
    @Schema(type = "string",
            example = "EA SPORTS FC 24 is the most true-to-football experience ever, " +
                    "with HyperMotionV, PlayStyles optimized by Opta, " +
                    "and a revolutionized Frostbite™ Engine that reinvents how over 19,000 authentic players move, play, and look in every match.")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    @Schema(type = "number", example = "119.9")
    private BigDecimal price;

    @NotBlank(message = "Category name is required")
    @Schema(type = "string", example = "Video Games")
    private String categoryName;

    @NotBlank(message = "Brand is required")
    @Schema(type = "string", example = "EA Sports")
    private String brandName;

    @URL
    @Schema(type = "string", example = "https://image.api.playstation.com/vulcan/ap/rnd/202307/0710/b5be3ed4fe3333949bc204af75d2f83e64706b6db50d0dd5.png")
    private String imageUri;

    private Boolean active;
    @JsonIgnore
    @PositiveOrZero
    private Long stock;

    private int rating = 0;

    @JsonIgnore
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonIgnore
    private String inventoryStatus;

}

