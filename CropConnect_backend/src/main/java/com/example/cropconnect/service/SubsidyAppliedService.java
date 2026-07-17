package com.example.cropconnect.service;

import com.example.cropconnect.dto.SubsidyAppliedDTO;
import java.util.List;

public interface SubsidyAppliedService {

    SubsidyAppliedDTO saveSubsidyApplied(SubsidyAppliedDTO dto);

    List<SubsidyAppliedDTO> getAllSubsidyAppliedRecords();

    SubsidyAppliedDTO getSubsidyAppliedById(Object id);

    SubsidyAppliedDTO updateSubsidyApplied(Object id, SubsidyAppliedDTO dto);

    void deleteSubsidyApplied(Object id);
}
