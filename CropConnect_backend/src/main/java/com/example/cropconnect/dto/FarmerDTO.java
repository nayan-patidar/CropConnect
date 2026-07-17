package com.example.cropconnect.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDTO {

    @NotNull(message = "Farmer ID is required")
    private Integer farmerId;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100)
    private String name;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid contact number")
    private String contact;

    @NotNull(message = "Registration number is required")
    private Integer regNo;

    @DecimalMin(value = "0.0")
    private BigDecimal sizeOwned;

}