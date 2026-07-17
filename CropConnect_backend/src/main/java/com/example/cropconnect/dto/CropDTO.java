package com.example.cropconnect.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CropDTO {

    @NotNull(message = "Crop ID is required")
    private Integer cropId;

    @NotBlank(message = "Crop name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Crop type is required")
    private String type;

    @NotBlank(message = "Season is required")
    private String season;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be greater than 0")
    private Integer duration;

}