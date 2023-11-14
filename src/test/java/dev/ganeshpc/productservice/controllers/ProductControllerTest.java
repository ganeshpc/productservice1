package dev.ganeshpc.productservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import dev.ganeshpc.productservice.dtos.GenericProductDto;
import dev.ganeshpc.productservice.exception.ProductNotFoundException;
import dev.ganeshpc.productservice.services.ProductService;

@SpringBootTest
public class ProductControllerTest {

    ProductController productController;

    @MockBean
    ProductService productService;

    GenericProductDto mockGenericProductDto1 = GenericProductDto.builder().id(1L).title("mockTitle")
            .description("mockDescription").price(100).category("mockCategory").build();

    GenericProductDto mockGenericProductDto2 = GenericProductDto.builder().id(2L).title("mockTitle")
            .description("mockDescription").price(100).category("mockCategory").build();

    GenericProductDto mockGenericProductDto3 = GenericProductDto.builder().id(3L).title("mockTitle")
            .description("mockDescription").price(100).category("mockCategory").build();

    @Autowired
    public ProductControllerTest(ProductController productController) {
        this.productController = productController;
    }

    @Test
    void shouldGetListOfGenericProductDto() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        genericProductDtos.add(mockGenericProductDto1);
        genericProductDtos.add(mockGenericProductDto2);
        genericProductDtos.add(mockGenericProductDto3);

        when(productService.getProducts()).thenReturn(genericProductDtos);

        List<GenericProductDto> controllerResponse = productController.getAllProducts();

        assertIterableEquals(genericProductDtos, controllerResponse);
    }

    @Test
    void shouldGetEmptyListOfGenericProductDtoIfNoProductsPresent() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        when(productService.getProducts()).thenReturn(genericProductDtos);

        List<GenericProductDto> controllerResponse = productController.getAllProducts();

        assertIterableEquals(genericProductDtos, controllerResponse);
    }

    @Test
    void shouldReturnProductWithGivenId() throws ProductNotFoundException {
        when(productService.getProductById(1L)).thenReturn(mockGenericProductDto1);

        GenericProductDto actualGenericProductDto = productController.getProductById(1L);

        genericProductDtoEqualAssertion(mockGenericProductDto1, actualGenericProductDto);
    }

    @Test
    void shouldThrowExceptionWhenProductIsNotPresent() throws ProductNotFoundException {
        when(productService.getProductById(33L)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(33L));
    }

    @Test
    void shouldDeleteProductIfPresent() throws ProductNotFoundException {
        when(productService.deleteProduct(1L)).thenReturn(mockGenericProductDto1);

        GenericProductDto deletedGenericProductDto = productController.deleteProductById(1L); 

        assertEquals(deletedGenericProductDto, mockGenericProductDto1);
    }

    @Test
    void shouldThrowExceptionWhenDeletingProductNotExists() throws ProductNotFoundException {
        when(productService.deleteProduct(1L)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> productController.deleteProductById(1L));
    }

    void genericProductDtoEqualAssertion(GenericProductDto expected, GenericProductDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getImage(), actual.getImage());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getCategory(), actual.getCategory());
    }
}
