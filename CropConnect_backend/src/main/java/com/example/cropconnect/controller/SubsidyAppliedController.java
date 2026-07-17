package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.SubsidyAppliedDTO;
import com.example.cropconnect.entity.SubsidyAppliedId;
import com.example.cropconnect.service.SubsidyAppliedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subsidyapplied")
@CrossOrigin("*")
public class SubsidyAppliedController {

    @Autowired
    private SubsidyAppliedService service;

    @PostMapping
    public ResponseEntity<ApiResponse<SubsidyAppliedDTO>> create(@Valid @RequestBody SubsidyAppliedDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveSubsidyApplied(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllSubsidyAppliedRecords()));
    }

    @GetMapping("/{farmerId}/{subsidyId}")
    public ResponseEntity<ApiResponse<SubsidyAppliedDTO>> getById(@PathVariable Integer farmerId,
                                                                    @PathVariable Integer subsidyId){
        SubsidyAppliedId id = new SubsidyAppliedId(farmerId, subsidyId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getSubsidyAppliedById(id)));
    }

    @PutMapping("/{farmerId}/{subsidyId}")
    public ResponseEntity<ApiResponse<SubsidyAppliedDTO>> update(@PathVariable Integer farmerId,
                                                                   @PathVariable Integer subsidyId,
                                                                   @Valid @RequestBody SubsidyAppliedDTO dto){
        SubsidyAppliedId id = new SubsidyAppliedId(farmerId, subsidyId);
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateSubsidyApplied(id,dto)));
    }

    @DeleteMapping("/{farmerId}/{subsidyId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer farmerId,
                                                        @PathVariable Integer subsidyId){
        SubsidyAppliedId id = new SubsidyAppliedId(farmerId, subsidyId);
        service.deleteSubsidyApplied(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
