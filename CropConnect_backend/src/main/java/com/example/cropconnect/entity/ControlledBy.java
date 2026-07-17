package com.example.cropconnect.entity;
import jakarta.persistence.*;import lombok.*;import java.time.LocalDate;
@Entity @Table(name="controlled_by") @IdClass(ControlledById.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ControlledBy{
 @Id @ManyToOne @JoinColumn(name="crop_id") private Crop crop;
 @Id @ManyToOne @JoinColumn(name="fertilizer_id") private Fertilizer fertilizer;
 @Id @Column(name="date_app") private LocalDate dateApp;
}
