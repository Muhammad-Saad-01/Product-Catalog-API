package net.muhammadsaad.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandModel {

    @JsonIgnore
    private long id;

    @Schema(example = "EA Sports")
    @NotNull
    private String name;

    @Schema(example = "EA Sports is a division of Electronic Arts that develops and publishes sports video games.")
    private String about;

    @URL
    @Schema(example = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/EA_Sports_Logo.svg/1200px-EA_Sports_Logo.svg.png")
    private String imageUri;

    @URL
    @Schema(example = "https://www.ea.com/")
    private String brandWebsiteUri;


    private boolean active;
}
