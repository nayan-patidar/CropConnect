package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 10)
    private String type;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

}