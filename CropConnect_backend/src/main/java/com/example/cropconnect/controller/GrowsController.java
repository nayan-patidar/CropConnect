package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.GrowsDTO;
import com.example.cropconnect.entity.GrowsId;
import com.example.cropconnect.service.GrowsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grows")
@CrossOrigin("*")
public class GrowsController {

    @Autowired
    private GrowsService service;

    @PostMapping
    public ResponseEntity<ApiResponse<GrowsDTO>> create(@Valid @RequestBody GrowsDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveGrows(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllGrowsRecords()));
    }

    @GetMapping("/{cropId}/{plotId}")
    public ResponseEntity<ApiResponse<GrowsDTO>> getById(@PathVariable Integer cropId,
                                                           @PathVariable Integer plotId){
        GrowsId id = new GrowsId(cropId, plotId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getGrowsById(id)));
    }

    @PutMapping("/{cropId}/{plotId}")
    public ResponseEntity<ApiResponse<GrowsDTO>> update(@PathVariable Integer cropId,
                                                          @PathVariable Integer plotId,
                                                          @Valid @RequestBody GrowsDTO dto){
        GrowsId id = new GrowsId(cropId, plotId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateGrows(id,dto)));
    }

    @DeleteMapping("/{cropId}/{plotId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer cropId,
                                                        @PathVariable Integer plotId){
        GrowsId id = new GrowsId(cropId, plotId);
        service.deleteGrows(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
