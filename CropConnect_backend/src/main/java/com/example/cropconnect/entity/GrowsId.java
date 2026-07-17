package com.example.cropconnect.entity;
import lombok.*;import java.io.Serializable;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class GrowsId implements Serializable{
 private Integer crop;
 private Integer plot;
}
