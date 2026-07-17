package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.BelongsToDTO;
import com.example.cropconnect.entity.BelongsTo;
import com.example.cropconnect.entity.BelongsToId;
import com.example.cropconnect.entity.Crop;
import com.example.cropconnect.entity.FarmPlot;
import com.example.cropconnect.entity.Harvest;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.BelongsToRepository;
import com.example.cropconnect.repository.CropRepository;
import com.example.cropconnect.repository.FarmPlotRepository;
import com.example.cropconnect.repository.HarvestRepository;
import com.example.cropconnect.service.BelongsToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BelongsToServiceImpl implements BelongsToService {

    @Autowired
    private BelongsToRepository belongsToRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private FarmPlotRepository farmPlotRepository;

    // DTO -> Entity
    private BelongsTo convertToEntity(BelongsToDTO dto) {

        Harvest harvest = harvestRepository.findById(dto.getHarvestId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Harvest not found with id : " + dto.getHarvestId()));

        Crop crop = cropRepository.findById(dto.getCropId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Crop not found with id : " + dto.getCropId()));

        FarmPlot plot = farmPlotRepository.findById(dto.getPlotId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farm Plot not found with id : " + dto.getPlotId()));

        BelongsTo belongsTo = new BelongsTo();

        belongsTo.setHarvest(harvest);
        belongsTo.setCrop(crop);
        belongsTo.setPlot(plot);

        return belongsTo;
    }

    // Entity -> DTO
    private BelongsToDTO convertToDTO(BelongsTo belongsTo) {

        BelongsToDTO dto = new BelongsToDTO();

        dto.setHarvestId(belongsTo.getHarvest().getHarvestId());
        dto.setCropId(belongsTo.getCrop().getCropId());
        dto.setPlotId(belongsTo.getPlot().getPlotId());

        return dto;
    }

    private BelongsToId toId(Object id) {

        if (id instanceof BelongsToId) {
            return (BelongsToId) id;
        }
        throw new ResourceNotFoundException("Invalid id supplied for BelongsTo");
    }

    @Override
    public BelongsToDTO saveBelongsTo(BelongsToDTO dto) {

        BelongsTo belongsTo = convertToEntity(dto);

        BelongsTo saved = belongsToRepository.save(belongsTo);

        return convertToDTO(saved);
    }

    @Override
    public List<BelongsToDTO> getAllBelongsToRecords() {

        return belongsToRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BelongsToDTO getBelongsToById(Object id) {

        BelongsTo belongsTo = belongsToRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BelongsTo record not found with id : " + id));

        return convertToDTO(belongsTo);
    }

    @Override
    public BelongsToDTO updateBelongsTo(Object id, BelongsToDTO dto) {

        BelongsTo belongsTo = belongsToRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BelongsTo record not found with id : " + id));

        // Composite key fields (harvest, crop, plot) define identity;
        // BelongsTo has no non-key attributes to update, so this re-saves the record.
        BelongsTo updated = belongsToRepository.save(belongsTo);

        return convertToDTO(updated);
    }

    @Override
    public void deleteBelongsTo(Object id) {

        BelongsTo belongsTo = belongsToRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "BelongsTo record not found with id : " + id));

        belongsToRepository.delete(belongsTo);
    }
}
