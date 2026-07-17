package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.ControlledByDTO;
import com.example.cropconnect.entity.ControlledBy;
import com.example.cropconnect.entity.ControlledById;
import com.example.cropconnect.entity.Crop;
import com.example.cropconnect.entity.Fertilizer;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.ControlledByRepository;
import com.example.cropconnect.repository.CropRepository;
import com.example.cropconnect.repository.FertilizerRepository;
import com.example.cropconnect.service.ControlledByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ControlledByServiceImpl implements ControlledByService {

    @Autowired
    private ControlledByRepository controlledByRepository;

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private FertilizerRepository fertilizerRepository;

    // DTO -> Entity
    private ControlledBy convertToEntity(ControlledByDTO dto) {

        Crop crop = cropRepository.findById(dto.getCropId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Crop not found with id : " + dto.getCropId()));

        Fertilizer fertilizer = fertilizerRepository.findById(dto.getFertilizerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fertilizer not found with id : " + dto.getFertilizerId()));

        ControlledBy controlledBy = new ControlledBy();

        controlledBy.setCrop(crop);
        controlledBy.setFertilizer(fertilizer);
        controlledBy.setDateApp(dto.getDateApp());

        return controlledBy;
    }

    // Entity -> DTO
    private ControlledByDTO convertToDTO(ControlledBy controlledBy) {

        ControlledByDTO dto = new ControlledByDTO();

        dto.setCropId(controlledBy.getCrop().getCropId());
        dto.setFertilizerId(controlledBy.getFertilizer().getFertilizerId());
        dto.setDateApp(controlledBy.getDateApp());

        return dto;
    }

    private ControlledById toId(Object id) {

        if (id instanceof ControlledById) {
            return (ControlledById) id;
        }
        throw new ResourceNotFoundException("Invalid id supplied for ControlledBy");
    }

    @Override
    public ControlledByDTO saveControlledBy(ControlledByDTO dto) {

        ControlledBy controlledBy = convertToEntity(dto);

        ControlledBy saved = controlledByRepository.save(controlledBy);

        return convertToDTO(saved);
    }

    @Override
    public List<ControlledByDTO> getAllControlledByRecords() {

        return controlledByRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ControlledByDTO getControlledByById(Object id) {

        ControlledBy controlledBy = controlledByRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "ControlledBy record not found with id : " + id));

        return convertToDTO(controlledBy);
    }

    @Override
    public ControlledByDTO updateControlledBy(Object id, ControlledByDTO dto) {

        ControlledBy controlledBy = controlledByRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "ControlledBy record not found with id : " + id));

        // Composite key fields (crop, fertilizer, dateApp) define identity;
        // ControlledBy has no non-key attributes to update, so this re-saves the record.
        ControlledBy updated = controlledByRepository.save(controlledBy);

        return convertToDTO(updated);
    }

    @Override
    public void deleteControlledBy(Object id) {

        ControlledBy controlledBy = controlledByRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "ControlledBy record not found with id : " + id));

        controlledByRepository.delete(controlledBy);
    }
}
