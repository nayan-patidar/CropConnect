package com.example.cropconnect.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class ControlledByDTO{
 private Integer cropId;
 private Integer fertilizerId;
 private LocalDate dateApp;
}
