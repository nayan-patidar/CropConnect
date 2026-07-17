package com.example.cropconnect.service;

import com.example.cropconnect.dto.CropDTO;

import java.util.List;

public interface CropService {

    CropDTO saveCrop(CropDTO cropDTO);

    List<CropDTO> getAllCrops();

    CropDTO getCropById(Integer id);

    CropDTO updateCrop(Integer id,CropDTO cropDTO);

    void deleteCrop(Integer id);

}