package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.GrowsDTO;
import com.example.cropconnect.entity.Crop;
import com.example.cropconnect.entity.FarmPlot;
import com.example.cropconnect.entity.Grows;
import com.example.cropconnect.entity.GrowsId;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.CropRepository;
import com.example.cropconnect.repository.FarmPlotRepository;
import com.example.cropconnect.repository.GrowsRepository;
import com.example.cropconnect.service.GrowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrowsServiceImpl implements GrowsService {

    @Autowired
    private GrowsRepository growsRepository;

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private FarmPlotRepository farmPlotRepository;

    // DTO -> Entity
    private Grows convertToEntity(GrowsDTO dto) {

        Crop crop = cropRepository.findById(dto.getCropId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Crop not found with id : " + dto.getCropId()));

        FarmPlot plot = farmPlotRepository.findById(dto.getPlotId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farm Plot not found with id : " + dto.getPlotId()));

        Grows grows = new Grows();

        grows.setCrop(crop);
        grows.setPlot(plot);
        grows.setStartDate(dto.getStartDate());
        grows.setEndDate(dto.getEndDate());

        return grows;
    }

    // Entity -> DTO
    private GrowsDTO convertToDTO(Grows grows) {

        GrowsDTO dto = new GrowsDTO();

        dto.setCropId(grows.getCrop().getCropId());
        dto.setPlotId(grows.getPlot().getPlotId());
        dto.setStartDate(grows.getStartDate());
        dto.setEndDate(grows.getEndDate());

        return dto;
    }

    private GrowsId toId(Object id) {

        if (id instanceof GrowsId) {
            return (GrowsId) id;
        }
        throw new ResourceNotFoundException("Invalid id supplied for Grows");
    }

    @Override
    public GrowsDTO saveGrows(GrowsDTO dto) {

        Grows grows = convertToEntity(dto);

        Grows saved = growsRepository.save(grows);

        return convertToDTO(saved);
    }

    @Override
    public List<GrowsDTO> getAllGrowsRecords() {

        return growsRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GrowsDTO getGrowsById(Object id) {

        Grows grows = growsRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Grows record not found with id : " + id));

        return convertToDTO(grows);
    }

    @Override
    public GrowsDTO updateGrows(Object id, GrowsDTO dto) {

        Grows grows = growsRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Grows record not found with id : " + id));

        grows.setStartDate(dto.getStartDate());
        grows.setEndDate(dto.getEndDate());

        Grows updated = growsRepository.save(grows);

        return convertToDTO(updated);
    }

    @Override
    public void deleteGrows(Object id) {

        Grows grows = growsRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Grows record not found with id : " + id));

        growsRepository.delete(grows);
    }
}
