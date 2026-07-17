package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.BelongsToDTO;
import com.example.cropconnect.entity.BelongsToId;
import com.example.cropconnect.service.BelongsToService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/belongsto")
@CrossOrigin("*")
public class BelongsToController {

    @Autowired
    private BelongsToService service;

    @PostMapping
    public ResponseEntity<ApiResponse<BelongsToDTO>> create(@Valid @RequestBody BelongsToDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveBelongsTo(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllBelongsToRecords()));
    }

    @GetMapping("/{harvestId}/{cropId}/{plotId}")
    public ResponseEntity<ApiResponse<BelongsToDTO>> getById(@PathVariable Integer harvestId,
                                                               @PathVariable Integer cropId,
                                                               @PathVariable Integer plotId){
        BelongsToId id = new BelongsToId(harvestId, cropId, plotId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getBelongsToById(id)));
    }

    @PutMapping("/{harvestId}/{cropId}/{plotId}")
    public ResponseEntity<ApiResponse<BelongsToDTO>> update(@PathVariable Integer harvestId,
                                                              @PathVariable Integer cropId,
                                                              @PathVariable Integer plotId,
                                                              @Valid @RequestBody BelongsToDTO dto){
        BelongsToId id = new BelongsToId(harvestId, cropId, plotId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateBelongsTo(id,dto)));
    }

    @DeleteMapping("/{harvestId}/{cropId}/{plotId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer harvestId,
                                                        @PathVariable Integer cropId,
                                                        @PathVariable Integer plotId){
        BelongsToId id = new BelongsToId(harvestId, cropId, plotId);
        service.deleteBelongsTo(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
