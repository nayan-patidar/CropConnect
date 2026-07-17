package com.example.cropconnect.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name="maintains_inventory_of")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MaintainsInventoryOf{
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="inventory_id")
 private Integer inventoryId;

 @ManyToOne @JoinColumn(name="warehouse_id",nullable=false)
 private Warehouse warehouse;

 @ManyToOne @JoinColumn(name="product_id",nullable=false)
 private Product product;

 @Column(nullable=false)
 private BigDecimal qty;

 @Column(name="storage_condition")
 private String storageCondition;
}
