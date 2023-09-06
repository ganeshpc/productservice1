package dev.ganeshpc.productservice.dtos;

import dev.ganeshpc.productservice.models.Category;
import dev.ganeshpc.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct() {
        Product product = new Product(title, description, image, new Category(category), price);
        return product;
    }

    public GenericProductDto toGenericProductDto() {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(title);
        genericProductDto.setDescription(description);
        genericProductDto.setPrice(price);
        genericProductDto.setCategory(category);
        genericProductDto.setImage(image);
        return genericProductDto;
    }
}
