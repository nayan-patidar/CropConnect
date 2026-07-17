package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.MarketDTO;
import com.example.cropconnect.service.MarketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/markets")
@CrossOrigin("*")
public class MarketController {

    @Autowired
    private MarketService service;

    @PostMapping
    public ResponseEntity<ApiResponse<MarketDTO>> create(@Valid @RequestBody MarketDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveMarket(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllMarkets()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MarketDTO>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getMarketById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MarketDTO>> update(@PathVariable Integer id,@Valid @RequestBody MarketDTO dto){
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateMarket(id,dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id){
        service.deleteMarket(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
