package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.CropDTO;
import com.example.cropconnect.service.CropService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping
    public ResponseEntity<ApiResponse<CropDTO>> saveCrop(
            @Valid @RequestBody CropDTO cropDTO) {

        CropDTO savedCrop = cropService.saveCrop(cropDTO);

        return new ResponseEntity<>(
                new ApiResponse<>(true,
                        "Crop added successfully",
                        savedCrop),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CropDTO>>> getAllCrops() {

        List<CropDTO> crops = cropService.getAllCrops();

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Crop list fetched successfully",
                        crops));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CropDTO>> getCropById(
            @PathVariable Integer id) {

        CropDTO crop = cropService.getCropById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Crop fetched successfully",
                        crop));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CropDTO>> updateCrop(
            @PathVariable Integer id,
            @Valid @RequestBody CropDTO cropDTO) {

        CropDTO updatedCrop = cropService.updateCrop(id, cropDTO);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Crop updated successfully",
                        updatedCrop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCrop(
            @PathVariable Integer id) {

        cropService.deleteCrop(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Crop deleted successfully",
                        null));
    }

}