package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.FarmPlotDTO;
import com.example.cropconnect.service.FarmPlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmplots")
@CrossOrigin("*")
public class FarmPlotController {

    @Autowired
    private FarmPlotService service;

    @PostMapping
    public ResponseEntity<ApiResponse<FarmPlotDTO>> create(@Valid @RequestBody FarmPlotDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveFarmPlot(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllFarmPlots()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FarmPlotDTO>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getFarmPlotById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FarmPlotDTO>> update(@PathVariable Integer id,@Valid @RequestBody FarmPlotDTO dto){
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateFarmPlot(id,dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id){
        service.deleteFarmPlot(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
