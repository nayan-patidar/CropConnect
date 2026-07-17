package com.example.cropconnect.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketDTO {

    @NotNull(message = "Market ID is required")
    private Integer marketId;

    @NotBlank(message = "Market name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "City is required")
    @Size(max = 100)
    private String city;

    @Pattern(
            regexp = "^(wholesale|retail)$",
            message = "Type must be wholesale or retail"
    )
    private String type;

}