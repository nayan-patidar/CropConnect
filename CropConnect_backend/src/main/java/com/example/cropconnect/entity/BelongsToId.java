package com.example.cropconnect.entity;
import lombok.*;import java.io.Serializable;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class BelongsToId implements Serializable{
 private Integer harvest; private Integer crop; private Integer plot;
}
