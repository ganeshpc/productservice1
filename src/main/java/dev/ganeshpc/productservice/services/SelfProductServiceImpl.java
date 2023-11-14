package dev.ganeshpc.productservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.ganeshpc.productservice.dtos.GenericProductDto;
import dev.ganeshpc.productservice.exception.ProductNotFoundException;
import dev.ganeshpc.productservice.models.Product;
import dev.ganeshpc.productservice.repositories.ProductRepository;

@Primary
@Service("SelfProductServiceImpl ")
public class SelfProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return toGenericProductDto(optionalProduct.get());
        } else {
            throw new ProductNotFoundException("Product with id: " + id + " is not present");
        }
    }

    @Override
    public List<GenericProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDto> genericProducts = new ArrayList<>();
        for (Product product : products) {
            genericProducts.add(toGenericProductDto(product));
        }

        return genericProducts;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = genericProductDto.toProduct();
        Product responseProduct = productRepository.save(product);
        return toGenericProductDto(responseProduct);
    }

    @Override
    public GenericProductDto deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Product with given id: " + id + " is not present");
        }
        productRepository.deleteById(id);
        return toGenericProductDto(optionalProduct.get());
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto) {
        Product product = genericProductDto.toProduct();
        Product updatedProduct = productRepository.save(product);
        return toGenericProductDto(updatedProduct);
    }

    GenericProductDto toGenericProductDto(Product product) {
        GenericProductDto.GenericProductDtoBuilder genericProductDtoBuilder = GenericProductDto.builder();
        genericProductDtoBuilder.id(product.getId()).title(product.getTitle()).description(product.getDescription())
                .image(product.getImage()).category(product.getCategory().getTitle())
                .price(product.getPrice().getPrice());
        return genericProductDtoBuilder.build();
    }

}
