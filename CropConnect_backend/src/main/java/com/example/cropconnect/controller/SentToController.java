package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.SentToDTO;
import com.example.cropconnect.entity.SentToId;
import com.example.cropconnect.service.SentToService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/sentto")
@CrossOrigin("*")
public class SentToController {

    @Autowired
    private SentToService service;

    @PostMapping
    public ResponseEntity<ApiResponse<SentToDTO>> create(@Valid @RequestBody SentToDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveSentTo(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllSentToRecords()));
    }

    @GetMapping("/{productId}/{marketId}/{vehicleId}/{date}")
    public ResponseEntity<ApiResponse<SentToDTO>> getById(@PathVariable Integer productId,
                                                            @PathVariable Integer marketId,
                                                            @PathVariable String vehicleId,
                                                            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        SentToId id = new SentToId(productId, marketId, vehicleId, date);
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getSentToById(id)));
    }

    @PutMapping("/{productId}/{marketId}/{vehicleId}/{date}")
    public ResponseEntity<ApiResponse<SentToDTO>> update(@PathVariable Integer productId,
                                                           @PathVariable Integer marketId,
                                                           @PathVariable String vehicleId,
                                                           @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                           @Valid @RequestBody SentToDTO dto){
        SentToId id = new SentToId(productId, marketId, vehicleId, date);
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateSentTo(id,dto)));
    }

    @DeleteMapping("/{productId}/{marketId}/{vehicleId}/{date}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer productId,
                                                        @PathVariable Integer marketId,
                                                        @PathVariable String vehicleId,
                                                        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        SentToId id = new SentToId(productId, marketId, vehicleId, date);
        service.deleteSentTo(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
