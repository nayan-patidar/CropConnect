package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "harvests")
public class Warehouse {

    @Id
    @Column(name = "warehouse_id")
    private Integer warehouseId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String city;

    @Column(name = "total_capacity", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCapacity;

    @Column(name = "left_capacity", precision = 10, scale = 2)
    private BigDecimal leftCapacity;

    @OneToMany(mappedBy = "warehouse")
    private List<Harvest> harvests;

}