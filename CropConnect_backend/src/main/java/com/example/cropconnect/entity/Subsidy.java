package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "subsidy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subsidy {

    @Id
    @Column(name = "subsidy_id")
    private Integer subsidyId;

    @Column(name = "scheme_name", nullable = false, length = 100)
    private String schemeName;

    private Integer eligibility;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    private Integer duration;

}