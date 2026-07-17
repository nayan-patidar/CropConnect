package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.FarmerDTO;
import com.example.cropconnect.entity.Farmer;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.FarmerRepository;
import com.example.cropconnect.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    // DTO -> Entity
    private Farmer convertToEntity(FarmerDTO dto) {

        Farmer farmer = new Farmer();

        farmer.setFarmerId(dto.getFarmerId());
        farmer.setName(dto.getName());
        farmer.setContact(dto.getContact());
        farmer.setRegNo(dto.getRegNo());
        farmer.setSizeOwned(dto.getSizeOwned());

        return farmer;
    }

    // Entity -> DTO
    private FarmerDTO convertToDTO(Farmer farmer) {

        FarmerDTO dto = new FarmerDTO();

        dto.setFarmerId(farmer.getFarmerId());
        dto.setName(farmer.getName());
        dto.setContact(farmer.getContact());
        dto.setRegNo(farmer.getRegNo());
        dto.setSizeOwned(farmer.getSizeOwned());

        return dto;
    }

    @Override
    public FarmerDTO saveFarmer(FarmerDTO farmerDTO) {

        Farmer farmer = convertToEntity(farmerDTO);

        Farmer saved = farmerRepository.save(farmer);

        return convertToDTO(saved);
    }

    @Override
    public List<FarmerDTO> getAllFarmers() {

        return farmerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FarmerDTO getFarmerById(Integer id) {

        Farmer farmer = farmerRepository.findById(id).orElse(null);

        if (farmer == null)
            throw new ResourceNotFoundException("Farmer not found with id : " + id);

        return convertToDTO(farmer);
    }

    @Override
    public FarmerDTO updateFarmer(Integer id, FarmerDTO farmerDTO) {

        Farmer farmer = farmerRepository.findById(id).orElse(null);

        if (farmer == null)
            return null;

        farmer.setName(farmerDTO.getName());
        farmer.setContact(farmerDTO.getContact());
        farmer.setRegNo(farmerDTO.getRegNo());
        farmer.setSizeOwned(farmerDTO.getSizeOwned());

        Farmer updated = farmerRepository.save(farmer);

        return convertToDTO(updated);
    }

    @Override
    public void deleteFarmer(Integer id) {

        farmerRepository.deleteById(id);

    }
}