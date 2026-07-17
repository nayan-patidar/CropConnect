package com.example.cropconnect.service;

import com.example.cropconnect.dto.MarketDTO;
import java.util.List;

public interface MarketService {

    MarketDTO saveMarket(MarketDTO marketDTO);

    List<MarketDTO> getAllMarkets();

    MarketDTO getMarketById(Integer id);

    MarketDTO updateMarket(Integer id, MarketDTO marketDTO);

    void deleteMarket(Integer id);

}