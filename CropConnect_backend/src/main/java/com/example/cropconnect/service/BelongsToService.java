package com.example.cropconnect.service;

import com.example.cropconnect.dto.BelongsToDTO;
import java.util.List;

public interface BelongsToService {

    BelongsToDTO saveBelongsTo(BelongsToDTO dto);

    List<BelongsToDTO> getAllBelongsToRecords();

    BelongsToDTO getBelongsToById(Object id);

    BelongsToDTO updateBelongsTo(Object id, BelongsToDTO dto);

    void deleteBelongsTo(Object id);
}
