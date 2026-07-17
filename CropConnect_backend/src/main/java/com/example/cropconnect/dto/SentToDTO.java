package com.example.cropconnect.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class SentToDTO{
 private Integer productId;
 private Integer marketId;
 private String vehicleId;
 private LocalDate date;
}
