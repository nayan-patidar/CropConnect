package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.SentToDTO;
import com.example.cropconnect.entity.Market;
import com.example.cropconnect.entity.Product;
import com.example.cropconnect.entity.SentTo;
import com.example.cropconnect.entity.SentToId;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.MarketRepository;
import com.example.cropconnect.repository.ProductRepository;
import com.example.cropconnect.repository.SentToRepository;
import com.example.cropconnect.service.SentToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SentToServiceImpl implements SentToService {

    @Autowired
    private SentToRepository sentToRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MarketRepository marketRepository;

    // DTO -> Entity
    private SentTo convertToEntity(SentToDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + dto.getProductId()));

        Market market = marketRepository.findById(dto.getMarketId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Market not found with id : " + dto.getMarketId()));

        SentTo sentTo = new SentTo();

        sentTo.setProduct(product);
        sentTo.setMarket(market);
        sentTo.setVehicleId(dto.getVehicleId());
        sentTo.setDate(dto.getDate());

        return sentTo;
    }

    // Entity -> DTO
    private SentToDTO convertToDTO(SentTo sentTo) {

        SentToDTO dto = new SentToDTO();

        dto.setProductId(sentTo.getProduct().getProductId());
        dto.setMarketId(sentTo.getMarket().getMarketId());
        dto.setVehicleId(sentTo.getVehicleId());
        dto.setDate(sentTo.getDate());

        return dto;
    }

    private SentToId toId(Object id) {

        if (id instanceof SentToId) {
            return (SentToId) id;
        }
        throw new ResourceNotFoundException("Invalid id supplied for SentTo");
    }

    @Override
    public SentToDTO saveSentTo(SentToDTO dto) {

        SentTo sentTo = convertToEntity(dto);

        SentTo saved = sentToRepository.save(sentTo);

        return convertToDTO(saved);
    }

    @Override
    public List<SentToDTO> getAllSentToRecords() {

        return sentToRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SentToDTO getSentToById(Object id) {

        SentTo sentTo = sentToRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "SentTo record not found with id : " + id));

        return convertToDTO(sentTo);
    }

    @Override
    public SentToDTO updateSentTo(Object id, SentToDTO dto) {

        SentTo sentTo = sentToRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "SentTo record not found with id : " + id));

        // Composite key fields (product, market, vehicleId, date) define identity;
        // SentTo has no non-key attributes to update, so this re-saves the record.
        SentTo updated = sentToRepository.save(sentTo);

        return convertToDTO(updated);
    }

    @Override
    public void deleteSentTo(Object id) {

        SentTo sentTo = sentToRepository.findById(toId(id))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "SentTo record not found with id : " + id));

        sentToRepository.delete(sentTo);
    }
}
