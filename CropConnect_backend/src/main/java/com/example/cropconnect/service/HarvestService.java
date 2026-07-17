package com.example.cropconnect.service;

import com.example.cropconnect.dto.HarvestDTO;
import java.util.List;

public interface HarvestService {

    HarvestDTO saveHarvest(HarvestDTO harvestDTO);

    List<HarvestDTO> getAllHarvests();

    HarvestDTO getHarvestById(Integer id);

    HarvestDTO updateHarvest(Integer id, HarvestDTO harvestDTO);

    void deleteHarvest(Integer id);

}