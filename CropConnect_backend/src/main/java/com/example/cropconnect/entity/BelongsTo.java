package com.example.cropconnect.entity;
import jakarta.persistence.*;import lombok.*;
@Entity @Table(name="belongs_to") @IdClass(BelongsToId.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BelongsTo{
 @Id @ManyToOne @JoinColumn(name="harvest_id") private Harvest harvest;
 @Id @ManyToOne @JoinColumn(name="crop_id") private Crop crop;
 @Id @ManyToOne @JoinColumn(name="plot_id") private FarmPlot plot;
}
