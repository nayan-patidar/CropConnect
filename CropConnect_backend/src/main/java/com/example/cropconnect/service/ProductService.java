package com.example.cropconnect.service;

import com.example.cropconnect.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    ProductDTO saveProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Integer id);

    ProductDTO updateProduct(Integer id, ProductDTO productDTO);

    void deleteProduct(Integer id);

}