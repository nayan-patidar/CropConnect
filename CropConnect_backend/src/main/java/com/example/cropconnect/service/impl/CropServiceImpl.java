package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.CropDTO;
import com.example.cropconnect.entity.Crop;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.CropRepository;
import com.example.cropconnect.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CropServiceImpl implements CropService {

    @Autowired
    private CropRepository cropRepository;

    // DTO -> Entity
    private Crop convertToEntity(CropDTO dto){

        Crop crop=new Crop();

        crop.setCropId(dto.getCropId());
        crop.setName(dto.getName());
        crop.setType(dto.getType());
        crop.setSeason(dto.getSeason());
        crop.setDuration(dto.getDuration());

        return crop;
    }

    // Entity -> DTO
    private CropDTO convertToDTO(Crop crop){

        CropDTO dto=new CropDTO();

        dto.setCropId(crop.getCropId());
        dto.setName(crop.getName());
        dto.setType(crop.getType());
        dto.setSeason(crop.getSeason());
        dto.setDuration(crop.getDuration());

        return dto;
    }

    @Override
    public CropDTO saveCrop(CropDTO cropDTO) {

        Crop crop=convertToEntity(cropDTO);

        Crop saved=cropRepository.save(crop);

        return convertToDTO(saved);
    }

    @Override
    public List<CropDTO> getAllCrops() {

        return cropRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public CropDTO getCropById(Integer id) {

        Crop crop=cropRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Crop not found with id : "+id));

        return convertToDTO(crop);

    }

    @Override
    public CropDTO updateCrop(Integer id, CropDTO cropDTO) {

        Crop crop=cropRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Crop not found with id : "+id));

        crop.setName(cropDTO.getName());
        crop.setType(cropDTO.getType());
        crop.setSeason(cropDTO.getSeason());
        crop.setDuration(cropDTO.getDuration());

        Crop updated=cropRepository.save(crop);

        return convertToDTO(updated);

    }

    @Override
    public void deleteCrop(Integer id) {

        Crop crop=cropRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Crop not found with id : "+id));

        cropRepository.delete(crop);

    }

}