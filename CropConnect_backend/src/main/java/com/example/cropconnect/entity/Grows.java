package com.example.cropconnect.entity;
import jakarta.persistence.*;import lombok.*;import java.time.LocalDate;
@Entity @Table(name="grows") @IdClass(GrowsId.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Grows{
 @Id @ManyToOne @JoinColumn(name="crop_id") private Crop crop;
 @Id @ManyToOne @JoinColumn(name="plot_id") private FarmPlot plot;
 @Column(name="start_date") private LocalDate startDate;
 @Column(name="end_date") private LocalDate endDate;
}
