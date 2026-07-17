package com.example.cropconnect.service;

import com.example.cropconnect.dto.MaintainsInventoryOfDTO;
import java.util.List;

public interface MaintainsInventoryOfService {

    MaintainsInventoryOfDTO saveMaintainsInventoryOf(MaintainsInventoryOfDTO dto);

    List<MaintainsInventoryOfDTO> getAllMaintainsInventoryRecords();

    MaintainsInventoryOfDTO getMaintainsInventoryOfById(Object id);

    MaintainsInventoryOfDTO updateMaintainsInventoryOf(Object id, MaintainsInventoryOfDTO dto);

    void deleteMaintainsInventoryOf(Object id);
}
