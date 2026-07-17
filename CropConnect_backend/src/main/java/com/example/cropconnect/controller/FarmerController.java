package com.example.cropconnect.controller;

import com.example.cropconnect.dto.FarmerDTO;
import com.example.cropconnect.service.FarmerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmers")
@CrossOrigin("*")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping
    public FarmerDTO saveFarmer(@Valid @RequestBody FarmerDTO farmerDTO) {

        return farmerService.saveFarmer(farmerDTO);

    }

    @GetMapping
    public List<FarmerDTO> getAllFarmers() {

        return farmerService.getAllFarmers();

    }

    @GetMapping("/{id}")
    public FarmerDTO getFarmerById(@PathVariable Integer id) {

        return farmerService.getFarmerById(id);

    }

    @PutMapping("/{id}")
    public FarmerDTO updateFarmer(@PathVariable Integer id,
                                  @Valid @RequestBody FarmerDTO farmerDTO) {

        return farmerService.updateFarmer(id, farmerDTO);

    }

    @DeleteMapping("/{id}")
    public String deleteFarmer(@PathVariable Integer id) {

        farmerService.deleteFarmer(id);

        return "Farmer deleted successfully.";

    }
}