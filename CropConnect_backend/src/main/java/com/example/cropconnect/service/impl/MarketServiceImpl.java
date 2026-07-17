package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.MarketDTO;
import com.example.cropconnect.entity.Market;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.MarketRepository;
import com.example.cropconnect.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;

    private Market convertToEntity(MarketDTO dto){

        Market market = new Market();

        market.setMarketId(dto.getMarketId());
        market.setName(dto.getName());
        market.setCity(dto.getCity());
        market.setType(dto.getType());

        return market;
    }

    private MarketDTO convertToDTO(Market market){

        MarketDTO dto = new MarketDTO();

        dto.setMarketId(market.getMarketId());
        dto.setName(market.getName());
        dto.setCity(market.getCity());
        dto.setType(market.getType());

        return dto;
    }

    @Override
    public MarketDTO saveMarket(MarketDTO marketDTO) {

        return convertToDTO(
                marketRepository.save(
                        convertToEntity(marketDTO)
                ));
    }

    @Override
    public List<MarketDTO> getAllMarkets() {

        return marketRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public MarketDTO getMarketById(Integer id) {

        Market market = marketRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Market not found with id : " + id));

        return convertToDTO(market);

    }

    @Override
    public MarketDTO updateMarket(Integer id,
                                  MarketDTO marketDTO) {

        Market market = marketRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Market not found with id : " + id));

        market.setName(marketDTO.getName());
        market.setCity(marketDTO.getCity());
        market.setType(marketDTO.getType());

        return convertToDTO(
                marketRepository.save(market)
        );

    }

    @Override
    public void deleteMarket(Integer id) {

        Market market = marketRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Market not found with id : " + id));

        marketRepository.delete(market);

    }
}