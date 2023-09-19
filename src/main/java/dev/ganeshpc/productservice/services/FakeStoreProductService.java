package dev.ganeshpc.productservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.ganeshpc.productservice.dtos.GenericProductDto;
import dev.ganeshpc.productservice.exception.ProductNotFoundException;
import dev.ganeshpc.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.ganeshpc.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;

@Primary
@Service
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException {
        return fakeStoreProductServiceClient.getProductById(id).toGenericProductDto();
    }

    @Override
    public List<GenericProductDto> getProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductServiceClient.getProducts();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            genericProductDtos.add(fakeStoreProductDto.toGenericProductDto());
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return fakeStoreProductServiceClient.createProduct(genericProductDto).toGenericProductDto();
    }

    @Override
    public GenericProductDto deleteProduct(Long id) throws ProductNotFoundException {
        return fakeStoreProductServiceClient.deleteProduct(id).toGenericProductDto();
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto) {
        return fakeStoreProductServiceClient.updateProductById(genericProductDto).toGenericProductDto();
    }

}
