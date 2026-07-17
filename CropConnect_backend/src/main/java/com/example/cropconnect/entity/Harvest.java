package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "harvest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "warehouse")
public class Harvest {

    @Id
    @Column(name = "harvest_id")
    private Integer harvestId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "total_yield", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalYield;

    @Column(name = "store_date")
    private LocalDate storeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

}