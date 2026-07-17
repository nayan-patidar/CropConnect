package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.SubsidyDTO;
import com.example.cropconnect.service.SubsidyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subsidys")
@CrossOrigin("*")
public class SubsidyController {

    @Autowired
    private SubsidyService service;

    @PostMapping
    public ResponseEntity<ApiResponse<SubsidyDTO>> create(@Valid @RequestBody SubsidyDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveSubsidy(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllSubsidies()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubsidyDTO>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getSubsidyById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SubsidyDTO>> update(@PathVariable Integer id,@Valid @RequestBody SubsidyDTO dto){
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateSubsidy(id,dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id){
        service.deleteSubsidy(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
