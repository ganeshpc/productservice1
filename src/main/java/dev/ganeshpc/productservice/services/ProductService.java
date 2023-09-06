package dev.ganeshpc.productservice.services;

import dev.ganeshpc.productservice.dtos.GenericProductDto;

public interface ProductService {
    
    GenericProductDto getProductById(Long id);

}
