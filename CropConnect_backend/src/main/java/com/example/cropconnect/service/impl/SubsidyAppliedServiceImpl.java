package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.SubsidyAppliedDTO;
import com.example.cropconnect.entity.Farmer;
import com.example.cropconnect.entity.Subsidy;
import com.example.cropconnect.entity.SubsidyApplied;
import com.example.cropconnect.entity.SubsidyAppliedId;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.FarmerRepository;
import com.example.cropconnect.repository.SubsidyAppliedRepository;
import com.example.cropconnect.repository.SubsidyRepository;
import com.example.cropconnect.service.SubsidyAppliedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubsidyAppliedServiceImpl implements SubsidyAppliedService {

    @Autowired
    private SubsidyAppliedRepository subsidyAppliedRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private SubsidyRepository subsidyRepository;

    // DTO -> Entity
    private SubsidyApplied convertToEntity(SubsidyAppliedDTO dto) {

        Farmer farmer = farmerRepository.findById(dto.getFarmerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farmer not found with id : " + dto.getFarmerId()));

        Subsidy subsidy = subsidyRepository.findById(dto.getSubsidyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subsidy not found with id : " + dto.getSubsidyId()));

        SubsidyApplied subsidyApplied = new SubsidyApplied();

        subsidyApplied.setFarmer(farmer);
        subsidyApplied.setSubsidy(subsidy);
        subsidyApplied.setStartDate(dto.getStartDate());
        subsidyApplied.setEndDate(dto.getEndDate());

        return subsidyApplied;
    }

    // Entity -> DTO
    private SubsidyAppliedDTO convertToDTO(SubsidyApplied subsidyApplied) {

        SubsidyAppliedDTO dto = new SubsidyAppliedDTO();

        dto.setFarmerId(subsidyApplied.getFarmer().getFarmerId());
        dto.setSubsidyId(subsidyApplied.getSubsidy().getSubsidyId());
        dto.setStartDate(subsidyApplied.getStartDate());
        dto.setEndDate(subsidyApplied.getEndDate());

        return dto;
    }

    private SubsidyAppliedId toId(Object id) {

        if (id instanceof SubsidyAppliedId) {
            return (SubsidyAppliedId) id;
        }
        throw new ResourceNotFoundException("Invalid id supplied for SubsidyApplied");
    }

    @Override
    public SubsidyAppliedDTO saveSubsidyApplied(SubsidyAppliedDTO dto) {

        SubsidyApplied subsidyApplied = convertToEntity(dto);

        SubsidyApplied saved = subsidyAppliedRepository.save(subsidyApplied);

        return convertToDTO(saved);
    }

    @Override
    public List<SubsidyAppliedDTO> getAllSubsidyAppliedRecords() {

        return subsidyAppliedRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubsidyAppliedDTO getSubsidyAppliedById(Object id) {

        SubsidyApplied subsidyApplied = subsidyAppliedRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "SubsidyApplied record not found with id : " + id));

        return convertToDTO(subsidyApplied);
    }

    @Override
    public SubsidyAppliedDTO updateSubsidyApplied(Object id, SubsidyAppliedDTO dto) {

        SubsidyApplied subsidyApplied = subsidyAppliedRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "SubsidyApplied record not found with id : " + id));

        subsidyApplied.setStartDate(dto.getStartDate());
        subsidyApplied.setEndDate(dto.getEndDate());

        SubsidyApplied updated = subsidyAppliedRepository.save(subsidyApplied);

        return convertToDTO(updated);
    }

    @Override
    public void deleteSubsidyApplied(Object id) {

        SubsidyApplied subsidyApplied = subsidyAppliedRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "SubsidyApplied record not found with id : " + id));

        subsidyAppliedRepository.delete(subsidyApplied);
    }
}
