package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fertilizer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fertilizer {

    @Id
    @Column(name = "fertilizer_id")
    private Integer fertilizerId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 20)
    private String composition;

}