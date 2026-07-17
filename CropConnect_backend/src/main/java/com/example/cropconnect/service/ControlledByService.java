package com.example.cropconnect.service;

import com.example.cropconnect.dto.ControlledByDTO;
import java.util.List;

public interface ControlledByService {

    ControlledByDTO saveControlledBy(ControlledByDTO dto);

    List<ControlledByDTO> getAllControlledByRecords();

    ControlledByDTO getControlledByById(Object id);

    ControlledByDTO updateControlledBy(Object id, ControlledByDTO dto);

    void deleteControlledBy(Object id);
}
