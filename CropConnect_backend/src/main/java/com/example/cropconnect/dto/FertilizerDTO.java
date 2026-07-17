package com.example.cropconnect.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FertilizerDTO {

    @NotNull(message = "Fertilizer ID is required")
    private Integer fertilizerId;

    @NotBlank(message = "Fertilizer name is required")
    @Size(max = 100)
    private String name;

    @Pattern(
            regexp = "^(Nitrogen|Phosphorous|Potassium|Calcium|Magnesium|Sulphur|Iron|Zinc|Copper)$",
            message = "Invalid fertilizer composition"
    )
    private String composition;

}