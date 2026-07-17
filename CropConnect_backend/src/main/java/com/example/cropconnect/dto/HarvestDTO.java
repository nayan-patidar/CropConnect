package com.example.cropconnect.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HarvestDTO {

    @NotNull(message = "Harvest ID is required")
    private Integer harvestId;

    @NotNull(message = "Harvest date is required")
    private LocalDate date;

    @NotNull(message = "Yield is required")
    @DecimalMin(value = "0.01")
    private BigDecimal totalYield;

    private LocalDate storeDate;

    @NotNull(message = "Warehouse ID is required")
    private Integer warehouseId;

}