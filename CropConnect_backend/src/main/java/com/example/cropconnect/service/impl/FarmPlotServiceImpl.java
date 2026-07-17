package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.FarmPlotDTO;
import com.example.cropconnect.entity.FarmPlot;
import com.example.cropconnect.entity.Farmer;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.FarmPlotRepository;
import com.example.cropconnect.repository.FarmerRepository;
import com.example.cropconnect.service.FarmPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmPlotServiceImpl implements FarmPlotService {

    @Autowired
    private FarmPlotRepository farmPlotRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    // DTO -> Entity
    private FarmPlot convertToEntity(FarmPlotDTO dto){

        Farmer farmer = farmerRepository.findById(dto.getFarmerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farmer not found with id : " + dto.getFarmerId()));

        FarmPlot farmPlot = new FarmPlot();

        farmPlot.setPlotId(dto.getPlotId());
        farmPlot.setSize(dto.getSize());
        farmPlot.setLocation(dto.getLocation());
        farmPlot.setStatus(dto.getStatus());
        farmPlot.setFarmer(farmer);

        return farmPlot;
    }

    // Entity -> DTO
    private FarmPlotDTO convertToDTO(FarmPlot farmPlot){

        FarmPlotDTO dto = new FarmPlotDTO();

        dto.setPlotId(farmPlot.getPlotId());
        dto.setSize(farmPlot.getSize());
        dto.setLocation(farmPlot.getLocation());
        dto.setStatus(farmPlot.getStatus());
        dto.setFarmerId(farmPlot.getFarmer().getFarmerId());

        return dto;
    }

    @Override
    public FarmPlotDTO saveFarmPlot(FarmPlotDTO farmPlotDTO) {

        FarmPlot farmPlot = convertToEntity(farmPlotDTO);

        FarmPlot saved = farmPlotRepository.save(farmPlot);

        return convertToDTO(saved);
    }

    @Override
    public List<FarmPlotDTO> getAllFarmPlots() {

        return farmPlotRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FarmPlotDTO getFarmPlotById(Integer id) {

        FarmPlot farmPlot = farmPlotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farm Plot not found with id : " + id));

        return convertToDTO(farmPlot);
    }

    @Override
    public FarmPlotDTO updateFarmPlot(Integer id,
                                      FarmPlotDTO farmPlotDTO) {

        FarmPlot farmPlot = farmPlotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farm Plot not found with id : " + id));

        Farmer farmer = farmerRepository.findById(
                        farmPlotDTO.getFarmerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farmer not found with id : "
                                        + farmPlotDTO.getFarmerId()));

        farmPlot.setSize(farmPlotDTO.getSize());
        farmPlot.setLocation(farmPlotDTO.getLocation());
        farmPlot.setStatus(farmPlotDTO.getStatus());
        farmPlot.setFarmer(farmer);

        FarmPlot updated = farmPlotRepository.save(farmPlot);

        return convertToDTO(updated);
    }

    @Override
    public void deleteFarmPlot(Integer id) {

        FarmPlot farmPlot = farmPlotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farm Plot not found with id : " + id));

        farmPlotRepository.delete(farmPlot);
    }
}