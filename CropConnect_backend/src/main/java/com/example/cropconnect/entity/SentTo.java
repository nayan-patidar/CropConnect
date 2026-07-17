package com.example.cropconnect.entity;
import jakarta.persistence.*;import lombok.*;import java.time.LocalDate;
@Entity @Table(name="sent_to") @IdClass(SentToId.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SentTo{
 @Id @ManyToOne @JoinColumn(name="product_id") private Product product;
 @Id @ManyToOne @JoinColumn(name="market_id") private Market market;
 @Id @Column(name="vehicle_id") private String vehicleId;
 @Id private LocalDate date;
}
