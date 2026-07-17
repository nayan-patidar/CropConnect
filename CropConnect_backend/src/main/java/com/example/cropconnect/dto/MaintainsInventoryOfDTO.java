package com.example.cropconnect.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class MaintainsInventoryOfDTO{
 private Integer inventoryId;
 private Integer warehouseId;
 private Integer productId;
 private BigDecimal qty;
 private String storageCondition;
}
