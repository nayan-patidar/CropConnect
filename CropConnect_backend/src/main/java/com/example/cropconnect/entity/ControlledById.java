package com.example.cropconnect.entity;
import lombok.*;import java.io.Serializable;import java.time.LocalDate;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class ControlledById implements Serializable{
 private Integer crop; private Integer fertilizer; private LocalDate dateApp;
}
