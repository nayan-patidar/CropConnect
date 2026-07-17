package com.example.cropconnect.entity;
import lombok.*;import java.io.Serializable;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class SubsidyAppliedId implements Serializable{
 private Integer farmer; private Integer subsidy;
}
