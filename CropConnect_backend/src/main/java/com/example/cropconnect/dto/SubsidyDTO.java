package com.example.cropconnect.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubsidyDTO {

    @NotNull(message = "Subsidy ID is required")
    private Integer subsidyId;

    @NotBlank(message = "Scheme name is required")
    @Size(max = 100)
    private String schemeName;

    @Min(value = 0)
    private Integer eligibility;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.00")
    private BigDecimal amount;

    @Min(value = 0)
    private Integer duration;

}