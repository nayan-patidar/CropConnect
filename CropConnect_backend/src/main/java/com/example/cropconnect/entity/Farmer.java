package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.math.BigDecimal;

@Entity
@Table(name = "farmer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "farmPlots")
public class Farmer {

    @Id
    @Column(name = "farmer_id")
    private Integer farmerId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "contact", length = 15)
    private String contact;

    @Column(name = "reg_no", nullable = false, unique = true)
    private Integer regNo;

    @Column(name = "size_owned", precision = 10, scale = 2)
    private BigDecimal sizeOwned;

    @OneToMany(mappedBy = "farmer")
    private List<FarmPlot> farmPlots;
}