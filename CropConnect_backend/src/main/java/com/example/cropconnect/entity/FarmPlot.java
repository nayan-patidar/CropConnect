package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "farm_plot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "farmer")
public class FarmPlot {

    @Id
    @Column(name = "plot_id")
    private Integer plotId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal size;

    @Column(length = 255)
    private String location;

    @Column(nullable = false, length = 15)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owned_by", nullable = false)
    private Farmer farmer;

}