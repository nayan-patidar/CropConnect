package com.example.cropconnect.service;

import com.example.cropconnect.dto.SentToDTO;
import java.util.List;

public interface SentToService {

    SentToDTO saveSentTo(SentToDTO dto);

    List<SentToDTO> getAllSentToRecords();

    SentToDTO getSentToById(Object id);

    SentToDTO updateSentTo(Object id, SentToDTO dto);

    void deleteSentTo(Object id);
}
