package dev.ganeshpc.productservice.controllers;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ganeshpc.productservice.dtos.ErrorDto;
import dev.ganeshpc.productservice.dtos.GenericProductDto;
import dev.ganeshpc.productservice.exception.ProductNotFoundException;
import dev.ganeshpc.productservice.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {

        GenericProductDto genericProduct = productService.deleteProduct(id);
        ResponseEntity<GenericProductDto> response = new ResponseEntity<>(genericProduct, HttpStatus.NOT_FOUND);
        return response;
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }

    @PutMapping("{id}")
    public void updateProductById() {

    }

    
}
