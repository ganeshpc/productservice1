package dev.ganeshpc.productservice.thirdpartyclients.productservice;

import dev.ganeshpc.productservice.dtos.GenericProductDto;
import dev.ganeshpc.productservice.exception.ProductNotFoundException;
import dev.ganeshpc.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;

public interface ThirdPartyProductServiceClient {
    FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException;

    FakeStoreProductDto[] getProducts();

    FakeStoreProductDto createProduct(GenericProductDto genericProductDto);

    FakeStoreProductDto deleteProduct(Long id) throws ProductNotFoundException;

    FakeStoreProductDto updateProductById(GenericProductDto genericProductDto);
}
