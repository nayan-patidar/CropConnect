package com.example.cropconnect.service;

import com.example.cropconnect.dto.FarmerDTO;

import java.util.List;

public interface FarmerService {

    FarmerDTO saveFarmer(FarmerDTO farmerDTO);

    List<FarmerDTO> getAllFarmers();

    FarmerDTO getFarmerById(Integer id);

    FarmerDTO updateFarmer(Integer id, FarmerDTO farmerDTO);

    void deleteFarmer(Integer id);

}