package com.example.cropconnect.entity;
import lombok.*;import java.io.Serializable;import java.time.LocalDate;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class SentToId implements Serializable{
 private Integer product; private Integer market; private String vehicleId; private LocalDate date;
}
