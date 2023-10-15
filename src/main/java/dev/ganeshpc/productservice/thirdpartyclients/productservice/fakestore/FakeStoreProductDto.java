package dev.ganeshpc.productservice.thirdpartyclients.productservice.fakestore;

import dev.ganeshpc.productservice.dtos.GenericProductDto;
import dev.ganeshpc.productservice.models.Category;
import dev.ganeshpc.productservice.models.Price;
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
        Product product = Product.builder().title(title).description(description).image(image)
                .category(new Category(category)).price(new Price(price)).build();
        return product;
    }

    public GenericProductDto toGenericProductDto() {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(id);
        genericProductDto.setTitle(title);
        genericProductDto.setDescription(description);
        genericProductDto.setPrice(price);
        genericProductDto.setCategory(category);
        genericProductDto.setImage(image);
        return genericProductDto;
    }
}
