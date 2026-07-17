package com.example.cropconnect.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class GrowsDTO{
 private Integer cropId;
 private Integer plotId;
 private LocalDate startDate;
 private LocalDate endDate;
}
