package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "crop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Crop {

    @Id
    @Column(name = "crop_id")
    private Integer cropId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "type", length = 20)
    private String type;

    @Column(name = "season", length = 10)
    private String season;

    @Column(name = "duration")
    private Integer duration;

}