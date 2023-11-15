package dev.ganeshpc.productservice.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ganeshpc.productservice.dtos.GenericProductDto;
import dev.ganeshpc.productservice.exception.ProductNotFoundException;
import dev.ganeshpc.productservice.services.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    GenericProductDto mockGenericProductDto1 = GenericProductDto.builder().id(1L).title("mockTitle")
            .description("mockDescription").price(100).category("mockCategory").build();

    @Test
    void shouldGetEmptyArrayWhenNoProductsPresent() throws Exception {
        when(productService.getProducts()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void shouldGetTheProductWithGivenId() throws Exception {
        when(productService.getProductById(1L)).thenReturn(mockGenericProductDto1);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(mockGenericProductDto1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("mockTitle")));
    }
}
