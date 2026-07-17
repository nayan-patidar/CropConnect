package com.example.cropconnect.controller;

import com.example.cropconnect.dto.ApiResponse;
import com.example.cropconnect.dto.ProductDTO;
import com.example.cropconnect.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> create(@Valid @RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,"Created",service.saveProduct(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true,"Success",service.getProductById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> update(@PathVariable Integer id,@Valid @RequestBody ProductDTO dto){
        return ResponseEntity.ok(new ApiResponse<>(true,"Updated",service.updateProduct(id,dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id){
        service.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Deleted","Record deleted"));
    }
}
