package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.SubsidyDTO;
import com.example.cropconnect.entity.Subsidy;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.SubsidyRepository;
import com.example.cropconnect.service.SubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubsidyServiceImpl implements SubsidyService {

    @Autowired
    private SubsidyRepository subsidyRepository;

    private Subsidy convertToEntity(SubsidyDTO dto){

        Subsidy subsidy = new Subsidy();

        subsidy.setSubsidyId(dto.getSubsidyId());
        subsidy.setSchemeName(dto.getSchemeName());
        subsidy.setEligibility(dto.getEligibility());
        subsidy.setAmount(dto.getAmount());
        subsidy.setDuration(dto.getDuration());

        return subsidy;
    }

    private SubsidyDTO convertToDTO(Subsidy subsidy){

        SubsidyDTO dto = new SubsidyDTO();

        dto.setSubsidyId(subsidy.getSubsidyId());
        dto.setSchemeName(subsidy.getSchemeName());
        dto.setEligibility(subsidy.getEligibility());
        dto.setAmount(subsidy.getAmount());
        dto.setDuration(subsidy.getDuration());

        return dto;
    }

    @Override
    public SubsidyDTO saveSubsidy(SubsidyDTO subsidyDTO) {

        return convertToDTO(
                subsidyRepository.save(
                        convertToEntity(subsidyDTO)
                ));
    }

    @Override
    public List<SubsidyDTO> getAllSubsidies() {

        return subsidyRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public SubsidyDTO getSubsidyById(Integer id) {

        Subsidy subsidy = subsidyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subsidy not found with id : " + id));

        return convertToDTO(subsidy);

    }

    @Override
    public SubsidyDTO updateSubsidy(Integer id,
                                    SubsidyDTO subsidyDTO) {

        Subsidy subsidy = subsidyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subsidy not found with id : " + id));

        subsidy.setSchemeName(subsidyDTO.getSchemeName());
        subsidy.setEligibility(subsidyDTO.getEligibility());
        subsidy.setAmount(subsidyDTO.getAmount());
        subsidy.setDuration(subsidyDTO.getDuration());

        return convertToDTO(
                subsidyRepository.save(subsidy)
        );

    }

    @Override
    public void deleteSubsidy(Integer id) {

        Subsidy subsidy = subsidyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subsidy not found with id : " + id));

        subsidyRepository.delete(subsidy);

    }
}