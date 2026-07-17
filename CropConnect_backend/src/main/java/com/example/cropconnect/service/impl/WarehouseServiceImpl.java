package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.WarehouseDTO;
import com.example.cropconnect.entity.Warehouse;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.WarehouseRepository;
import com.example.cropconnect.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    private Warehouse convertToEntity(WarehouseDTO dto){

        Warehouse warehouse = new Warehouse();

        warehouse.setWarehouseId(dto.getWarehouseId());
        warehouse.setName(dto.getName());
        warehouse.setCity(dto.getCity());
        warehouse.setTotalCapacity(dto.getTotalCapacity());
        warehouse.setLeftCapacity(dto.getLeftCapacity());

        return warehouse;
    }

    private WarehouseDTO convertToDTO(Warehouse warehouse){

        WarehouseDTO dto = new WarehouseDTO();

        dto.setWarehouseId(warehouse.getWarehouseId());
        dto.setName(warehouse.getName());
        dto.setCity(warehouse.getCity());
        dto.setTotalCapacity(warehouse.getTotalCapacity());
        dto.setLeftCapacity(warehouse.getLeftCapacity());

        return dto;
    }

    @Override
    public WarehouseDTO saveWarehouse(WarehouseDTO warehouseDTO) {

        Warehouse warehouse = convertToEntity(warehouseDTO);

        return convertToDTO(
                warehouseRepository.save(warehouse)
        );
    }

    @Override
    public List<WarehouseDTO> getAllWarehouses() {

        return warehouseRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public WarehouseDTO getWarehouseById(Integer id) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id : " + id));

        return convertToDTO(warehouse);

    }

    @Override
    public WarehouseDTO updateWarehouse(Integer id,
                                        WarehouseDTO warehouseDTO) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id : " + id));

        warehouse.setName(warehouseDTO.getName());
        warehouse.setCity(warehouseDTO.getCity());
        warehouse.setTotalCapacity(warehouseDTO.getTotalCapacity());
        warehouse.setLeftCapacity(warehouseDTO.getLeftCapacity());

        return convertToDTO(
                warehouseRepository.save(warehouse)
        );

    }

    @Override
    public void deleteWarehouse(Integer id) {

        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id : " + id));

        warehouseRepository.delete(warehouse);

    }
}