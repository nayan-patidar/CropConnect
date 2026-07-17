package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.HarvestDTO;
import com.example.cropconnect.service.HarvestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvests")
@CrossOrigin("*")
public class HarvestController {

    @Autowired
    private HarvestService service;

    @PostMapping
    public ResponseEntity<ApiResponse<HarvestDTO>> create(@Valid @RequestBody HarvestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveHarvest(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllHarvests()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HarvestDTO>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getHarvestById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HarvestDTO>> update(@PathVariable Integer id,@Valid @RequestBody HarvestDTO dto){
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateHarvest(id,dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id){
        service.deleteHarvest(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
