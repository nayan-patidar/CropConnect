package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.WarehouseDTO;
import com.example.cropconnect.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouses")
@CrossOrigin("*")
public class WarehouseController {

    @Autowired
    private WarehouseService service;

    @PostMapping
    public ResponseEntity<ApiResponse<WarehouseDTO>> create(@Valid @RequestBody WarehouseDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveWarehouse(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllWarehouses()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseDTO>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getWarehouseById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseDTO>> update(@PathVariable Integer id,@Valid @RequestBody WarehouseDTO dto){
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateWarehouse(id,dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id){
        service.deleteWarehouse(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
