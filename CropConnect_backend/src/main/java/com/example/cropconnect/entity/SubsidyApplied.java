package com.example.cropconnect.entity;
import jakarta.persistence.*;import lombok.*;import java.time.LocalDate;
@Entity @Table(name="subsidy_applied") @IdClass(SubsidyAppliedId.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SubsidyApplied{
 @Id @ManyToOne @JoinColumn(name="farmer_id") private Farmer farmer;
 @Id @ManyToOne @JoinColumn(name="subsidy_id") private Subsidy subsidy;
 @Column(name="start_date") private LocalDate startDate;
 @Column(name="end_date") private LocalDate endDate;
}
