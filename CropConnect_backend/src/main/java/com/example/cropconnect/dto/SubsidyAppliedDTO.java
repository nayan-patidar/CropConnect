package com.example.cropconnect.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class SubsidyAppliedDTO{
 private Integer farmerId;
 private Integer subsidyId;
 private LocalDate startDate;
 private LocalDate endDate;
}
