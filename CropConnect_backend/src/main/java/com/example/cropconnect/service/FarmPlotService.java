package com.example.cropconnect.service;

import com.example.cropconnect.dto.FarmPlotDTO;
import java.util.List;

public interface FarmPlotService {

    FarmPlotDTO saveFarmPlot(FarmPlotDTO farmPlotDTO);

    List<FarmPlotDTO> getAllFarmPlots();

    FarmPlotDTO getFarmPlotById(Integer id);

    FarmPlotDTO updateFarmPlot(Integer id, FarmPlotDTO farmPlotDTO);

    void deleteFarmPlot(Integer id);

}