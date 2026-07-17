package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "market")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Market {

    @Id
    @Column(name = "market_id")
    private Integer marketId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String city;

    @Column(nullable = false, length = 10)
    private String type;

}