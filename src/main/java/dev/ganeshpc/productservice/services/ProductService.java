package dev.ganeshpc.productservice.services;

import java.util.List;

import dev.ganeshpc.productservice.dtos.GenericProductDto;
import dev.ganeshpc.productservice.exception.ProductNotFoundException;

public interface ProductService {
    
    GenericProductDto getProductById(Long id) throws ProductNotFoundException;
    List<GenericProductDto> getProducts();
    GenericProductDto createProduct(GenericProductDto genericProductDto);
    GenericProductDto deleteProduct(Long id);
}
