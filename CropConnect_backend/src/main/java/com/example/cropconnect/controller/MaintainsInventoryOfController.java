package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.MaintainsInventoryOfDTO;
import com.example.cropconnect.service.MaintainsInventoryOfService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class MaintainsInventoryOfController {

    @Autowired
    private MaintainsInventoryOfService service;

    @PostMapping
    public ResponseEntity<ApiResponse<MaintainsInventoryOfDTO>> create(@Valid @RequestBody MaintainsInventoryOfDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveMaintainsInventoryOf(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllMaintainsInventoryRecords()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintainsInventoryOfDTO>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getMaintainsInventoryOfById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintainsInventoryOfDTO>> update(@PathVariable Integer id,
                                                                         @Valid @RequestBody MaintainsInventoryOfDTO dto){
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateMaintainsInventoryOf(id,dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id){
        service.deleteMaintainsInventoryOf(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
