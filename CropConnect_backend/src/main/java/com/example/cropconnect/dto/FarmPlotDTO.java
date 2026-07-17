package com.example.cropconnect.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmPlotDTO {

    @NotNull(message = "Plot ID is required")
    private Integer plotId;

    @NotNull(message = "Size is required")
    @DecimalMin(value = "0.01", message = "Size must be greater than 0")
    private BigDecimal size;

    @NotBlank(message = "Location is required")
    @Size(max = 255)
    private String location;

    @Pattern(
            regexp = "^(vacant|cultivated)$",
            message = "Status must be vacant or cultivated"
    )
    private String status;

    @NotNull(message = "Farmer ID is required")
    private Integer farmerId;

}