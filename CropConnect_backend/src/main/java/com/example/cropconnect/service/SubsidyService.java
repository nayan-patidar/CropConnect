package com.example.cropconnect.service;

import com.example.cropconnect.dto.SubsidyDTO;
import java.util.List;

public interface SubsidyService {

    SubsidyDTO saveSubsidy(SubsidyDTO subsidyDTO);

    List<SubsidyDTO> getAllSubsidies();

    SubsidyDTO getSubsidyById(Integer id);

    SubsidyDTO updateSubsidy(Integer id, SubsidyDTO subsidyDTO);

    void deleteSubsidy(Integer id);

}