package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.FertilizerDTO;
import com.example.cropconnect.entity.Fertilizer;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.FertilizerRepository;
import com.example.cropconnect.service.FertilizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FertilizerServiceImpl implements FertilizerService {

    @Autowired
    private FertilizerRepository fertilizerRepository;

    private Fertilizer convertToEntity(FertilizerDTO dto){

        Fertilizer fertilizer = new Fertilizer();

        fertilizer.setFertilizerId(dto.getFertilizerId());
        fertilizer.setName(dto.getName());
        fertilizer.setComposition(dto.getComposition());

        return fertilizer;
    }

    private FertilizerDTO convertToDTO(Fertilizer fertilizer){

        FertilizerDTO dto = new FertilizerDTO();

        dto.setFertilizerId(fertilizer.getFertilizerId());
        dto.setName(fertilizer.getName());
        dto.setComposition(fertilizer.getComposition());

        return dto;
    }

    @Override
    public FertilizerDTO saveFertilizer(FertilizerDTO fertilizerDTO) {

        return convertToDTO(
                fertilizerRepository.save(
                        convertToEntity(fertilizerDTO)
                ));
    }

    @Override
    public List<FertilizerDTO> getAllFertilizers() {

        return fertilizerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public FertilizerDTO getFertilizerById(Integer id) {

        Fertilizer fertilizer = fertilizerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fertilizer not found with id : " + id));

        return convertToDTO(fertilizer);

    }

    @Override
    public FertilizerDTO updateFertilizer(Integer id,
                                          FertilizerDTO fertilizerDTO) {

        Fertilizer fertilizer = fertilizerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fertilizer not found with id : " + id));

        fertilizer.setName(fertilizerDTO.getName());
        fertilizer.setComposition(fertilizerDTO.getComposition());

        return convertToDTO(
                fertilizerRepository.save(fertilizer)
        );

    }

    @Override
    public void deleteFertilizer(Integer id) {

        Fertilizer fertilizer = fertilizerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fertilizer not found with id : " + id));

        fertilizerRepository.delete(fertilizer);

    }
}