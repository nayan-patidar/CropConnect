package com.example.cropconnect.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotNull(message = "Product ID is required")
    private Integer productId;

    @NotBlank(message = "Product name is required")
    @Size(max = 100)
    private String name;

    @Pattern(
            regexp = "^(raw|processed)$",
            message = "Type must be raw or processed"
    )
    private String type;

    @DecimalMin(value = "0.00")
    private BigDecimal unitPrice;

}