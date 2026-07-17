package com.example.cropconnect.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {

    @NotNull(message = "Warehouse ID is required")
    private Integer warehouseId;

    @NotBlank(message = "Warehouse name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "City is required")
    @Size(max = 100)
    private String city;

    @NotNull(message = "Total capacity is required")
    @DecimalMin(value = "0.01")
    private BigDecimal totalCapacity;

    @DecimalMin(value = "0.00")
    private BigDecimal leftCapacity;

}