package com.example.cropconnect.service;

import com.example.cropconnect.dto.WarehouseDTO;
import java.util.List;

public interface WarehouseService {

    WarehouseDTO saveWarehouse(WarehouseDTO warehouseDTO);

    List<WarehouseDTO> getAllWarehouses();

    WarehouseDTO getWarehouseById(Integer id);

    WarehouseDTO updateWarehouse(Integer id, WarehouseDTO warehouseDTO);

    void deleteWarehouse(Integer id);

}