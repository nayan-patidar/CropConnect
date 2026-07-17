package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.ControlledByDTO;
import com.example.cropconnect.entity.ControlledById;
import com.example.cropconnect.service.ControlledByService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/controlledby")
@CrossOrigin("*")
public class ControlledByController {

    @Autowired
    private ControlledByService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ControlledByDTO>> create(@Valid @RequestBody ControlledByDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveControlledBy(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllControlledByRecords()));
    }

    @GetMapping("/{cropId}/{fertilizerId}/{dateApp}")
    public ResponseEntity<ApiResponse<ControlledByDTO>> getById(@PathVariable Integer cropId,
                                                                  @PathVariable Integer fertilizerId,
                                                                  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateApp){
        ControlledById id = new ControlledById(cropId, fertilizerId, dateApp);
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getControlledByById(id)));
    }

    @PutMapping("/{cropId}/{fertilizerId}/{dateApp}")
    public ResponseEntity<ApiResponse<ControlledByDTO>> update(@PathVariable Integer cropId,
                                                                 @PathVariable Integer fertilizerId,
                                                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateApp,
                                                                 @Valid @RequestBody ControlledByDTO dto){
        ControlledById id = new ControlledById(cropId, fertilizerId, dateApp);
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateControlledBy(id,dto)));
    }

    @DeleteMapping("/{cropId}/{fertilizerId}/{dateApp}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer cropId,
                                                        @PathVariable Integer fertilizerId,
                                                        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateApp){
        ControlledById id = new ControlledById(cropId, fertilizerId, dateApp);
        service.deleteControlledBy(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
