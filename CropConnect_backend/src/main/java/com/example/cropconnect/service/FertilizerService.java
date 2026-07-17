package com.example.cropconnect.service;

import com.example.cropconnect.dto.FertilizerDTO;
import java.util.List;

public interface FertilizerService {

    FertilizerDTO saveFertilizer(FertilizerDTO fertilizerDTO);

    List<FertilizerDTO> getAllFertilizers();

    FertilizerDTO getFertilizerById(Integer id);

    FertilizerDTO updateFertilizer(Integer id, FertilizerDTO fertilizerDTO);

    void deleteFertilizer(Integer id);

}