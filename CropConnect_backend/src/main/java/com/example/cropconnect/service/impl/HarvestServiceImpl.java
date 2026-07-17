package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.HarvestDTO;
import com.example.cropconnect.entity.Harvest;
import com.example.cropconnect.entity.Warehouse;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.HarvestRepository;
import com.example.cropconnect.repository.WarehouseRepository;
import com.example.cropconnect.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestServiceImpl implements HarvestService {

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    private Harvest convertToEntity(HarvestDTO dto){

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id : "
                                        + dto.getWarehouseId()));

        Harvest harvest = new Harvest();

        harvest.setHarvestId(dto.getHarvestId());
        harvest.setDate(dto.getDate());
        harvest.setTotalYield(dto.getTotalYield());
        harvest.setStoreDate(dto.getStoreDate());
        harvest.setWarehouse(warehouse);

        return harvest;
    }

    private HarvestDTO convertToDTO(Harvest harvest){

        HarvestDTO dto = new HarvestDTO();

        dto.setHarvestId(harvest.getHarvestId());
        dto.setDate(harvest.getDate());
        dto.setTotalYield(harvest.getTotalYield());
        dto.setStoreDate(harvest.getStoreDate());
        dto.setWarehouseId(
                harvest.getWarehouse().getWarehouseId()
        );

        return dto;
    }

    @Override
    public HarvestDTO saveHarvest(HarvestDTO harvestDTO) {

        Harvest harvest = convertToEntity(harvestDTO);

        return convertToDTO(
                harvestRepository.save(harvest)
        );

    }

    @Override
    public List<HarvestDTO> getAllHarvests() {

        return harvestRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public HarvestDTO getHarvestById(Integer id) {

        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Harvest not found with id : " + id));

        return convertToDTO(harvest);

    }

    @Override
    public HarvestDTO updateHarvest(Integer id,
                                    HarvestDTO harvestDTO) {

        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Harvest not found with id : " + id));

        Warehouse warehouse = warehouseRepository.findById(
                        harvestDTO.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found with id : "
                                        + harvestDTO.getWarehouseId()));

        harvest.setDate(harvestDTO.getDate());
        harvest.setTotalYield(harvestDTO.getTotalYield());
        harvest.setStoreDate(harvestDTO.getStoreDate());
        harvest.setWarehouse(warehouse);

        return convertToDTO(
                harvestRepository.save(harvest)
        );

    }

    @Override
    public void deleteHarvest(Integer id) {

        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Harvest not found with id : " + id));

        harvestRepository.delete(harvest);

    }
}