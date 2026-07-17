package com.example.cropconnect.service.impl;

import com.example.cropconnect.dto.ProductDTO;
import com.example.cropconnect.entity.Product;
import com.example.cropconnect.exception.ResourceNotFoundException;
import com.example.cropconnect.repository.ProductRepository;
import com.example.cropconnect.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private Product convertToEntity(ProductDTO dto){

        Product product = new Product();

        product.setProductId(dto.getProductId());
        product.setName(dto.getName());
        product.setType(dto.getType());
        product.setUnitPrice(dto.getUnitPrice());

        return product;
    }

    private ProductDTO convertToDTO(Product product){

        ProductDTO dto = new ProductDTO();

        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setType(product.getType());
        dto.setUnitPrice(product.getUnitPrice());

        return dto;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {

        return convertToDTO(
                productRepository.save(
                        convertToEntity(productDTO)
                ));
    }

    @Override
    public List<ProductDTO> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public ProductDTO getProductById(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + id));

        return convertToDTO(product);

    }

    @Override
    public ProductDTO updateProduct(Integer id,
                                    ProductDTO productDTO) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + id));

        product.setName(productDTO.getName());
        product.setType(productDTO.getType());
        product.setUnitPrice(productDTO.getUnitPrice());

        return convertToDTO(
                productRepository.save(product)
        );

    }

    @Override
    public void deleteProduct(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id : " + id));

        productRepository.delete(product);

    }
}