package dev.ganeshpc.productservice.thirdpartyclients.productservice.fakestore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import dev.ganeshpc.productservice.dtos.GenericProductDto;
import dev.ganeshpc.productservice.exception.ProductNotFoundException;
import dev.ganeshpc.productservice.thirdpartyclients.productservice.ThirdPartyProductServiceClient;

/**
 * Wrapper over Fakestore API's
 * Representation of API's provided by Fakestore
 */
@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;

    private String fakeStoreApiUrl;

    private String fakeStoreProductsApiPath;

    private String specificProductRequestUrl;
    private String productRequestBaseUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
            @Value("${fakestore.api.url:https://fakestoreapi.com}") String fakeStoreApiUrl,
            @Value("${fakestore.api.paths.products:/products}") String fakeStoreProductsApiPath) {
        this.fakeStoreApiUrl = fakeStoreApiUrl;
        this.fakeStoreProductsApiPath = fakeStoreProductsApiPath;
        this.restTemplateBuilder = restTemplateBuilder;
        this.specificProductRequestUrl = this.fakeStoreApiUrl + this.fakeStoreProductsApiPath + "/{id}";
        this.productRequestBaseUrl = this.fakeStoreApiUrl + this.fakeStoreProductsApiPath;
    }

    @Override
    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl,
                FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id: " + id + " does'nt exist");
        }

        return fakeStoreProductDto;
    }

    @Override
    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseUrl,
                genericProductDto, FakeStoreProductDto.class);
        return response.getBody();
    }

    @Override
    public FakeStoreProductDto[] getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl,
                FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();

        return fakeStoreProductDtos;
    }

    @Override
    public FakeStoreProductDto deleteProduct(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate
                .responseEntityExtractor(FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id)
                .getBody();

        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id: " + id + " does'nt exist");
        }

        return fakeStoreProductDto;
    }

    @Override
    public FakeStoreProductDto updateProductById(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate
                .responseEntityExtractor(FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT,
                requestCallback, responseExtractor, genericProductDto.getId()).getBody();

        return fakeStoreProductDto;
    }
}
