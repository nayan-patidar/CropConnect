package com.example.cropconnect.service;

import com.example.cropconnect.dto.GrowsDTO;
import java.util.List;

public interface GrowsService {

    GrowsDTO saveGrows(GrowsDTO dto);

    List<GrowsDTO> getAllGrowsRecords();

    GrowsDTO getGrowsById(Object id);

    GrowsDTO updateGrows(Object id, GrowsDTO dto);

    void deleteGrows(Object id);
}
