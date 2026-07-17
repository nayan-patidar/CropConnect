package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.MaintainsInventoryOfDTO;
import com.example.cropconnect.entity.MaintainsInventoryOf;
import com.example.cropconnect.entity.Product;
import com.example.cropconnect.entity.Warehouse;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.MaintainsInventoryOfRepository;
import com.example.cropconnect.repository.ProductRepository;
import com.example.cropconnect.repository.WarehouseRepository;
import com.example.cropconnect.service.MaintainsInventoryOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintainsInventoryOfServiceImpl implements MaintainsInventoryOfService {

    @Autowired
    private MaintainsInventoryOfRepository inventoryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    // DTO -> Entity
    private MaintainsInventoryOf convertToEntity(MaintainsInventoryOfDTO dto) {

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id : " + dto.getWarehouseId()));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + dto.getProductId()));

        MaintainsInventoryOf inventory = new MaintainsInventoryOf();

        inventory.setInventoryId(dto.getInventoryId());
        inventory.setWarehouse(warehouse);
        inventory.setProduct(product);
        inventory.setQty(dto.getQty());
        inventory.setStorageCondition(dto.getStorageCondition());

        return inventory;
    }

    // Entity -> DTO
    private MaintainsInventoryOfDTO convertToDTO(MaintainsInventoryOf inventory) {

        MaintainsInventoryOfDTO dto = new MaintainsInventoryOfDTO();

        dto.setInventoryId(inventory.getInventoryId());
        dto.setWarehouseId(inventory.getWarehouse().getWarehouseId());
        dto.setProductId(inventory.getProduct().getProductId());
        dto.setQty(inventory.getQty());
        dto.setStorageCondition(inventory.getStorageCondition());

        return dto;
    }

    @Override
    public MaintainsInventoryOfDTO saveMaintainsInventoryOf(MaintainsInventoryOfDTO dto) {

        MaintainsInventoryOf inventory = convertToEntity(dto);

        MaintainsInventoryOf saved = inventoryRepository.save(inventory);

        return convertToDTO(saved);
    }

    @Override
    public List<MaintainsInventoryOfDTO> getAllMaintainsInventoryRecords() {

        return inventoryRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MaintainsInventoryOfDTO getMaintainsInventoryOfById(Object id) {

        Integer inventoryId = (Integer) id;

        MaintainsInventoryOf inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory record not found with id : " + id));

        return convertToDTO(inventory);
    }

    @Override
    public MaintainsInventoryOfDTO updateMaintainsInventoryOf(Object id, MaintainsInventoryOfDTO dto) {

        Integer inventoryId = (Integer) id;

        MaintainsInventoryOf inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory record not found with id : " + id));

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id : " + dto.getWarehouseId()));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + dto.getProductId()));

        inventory.setWarehouse(warehouse);
        inventory.setProduct(product);
        inventory.setQty(dto.getQty());
        inventory.setStorageCondition(dto.getStorageCondition());

        MaintainsInventoryOf updated = inventoryRepository.save(inventory);

        return convertToDTO(updated);
    }

    @Override
    public void deleteMaintainsInventoryOf(Object id) {

        Integer inventoryId = (Integer) id;

        MaintainsInventoryOf inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory record not found with id : " + id));

        inventoryRepository.delete(inventory);
    }
}
