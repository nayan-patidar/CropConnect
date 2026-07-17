package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.FertilizerDTO;
import com.example.cropconnect.service.FertilizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fertilizers")
@CrossOrigin("*")
public class FertilizerController {

    @Autowired
    private FertilizerService service;

    @PostMapping
    public ResponseEntity<ApiResponse<FertilizerDTO>> create(@Valid @RequestBody FertilizerDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveFertilizer(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllFertilizers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FertilizerDTO>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getFertilizerById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FertilizerDTO>> update(@PathVariable Integer id,@Valid @RequestBody FertilizerDTO dto){
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateFertilizer(id,dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id){
        service.deleteFertilizer(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
