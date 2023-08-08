package net.muhammadsaad.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryModel {

    private Long id;

    @NotBlank(message = "Name is required")
    @Schema(type = "string", example = "Video Games")
    @Size(max = 255, message = "Name must be at most 255 characters")
    private String name;

    @Schema(type = "string", example = "Video games are electronic games that involves interaction with a user interface or input device – such as a joystick, controller, keyboard, or motion sensing device – to generate visual feedback for a player.")
    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    private String imageUri;

    private Boolean active;
}
