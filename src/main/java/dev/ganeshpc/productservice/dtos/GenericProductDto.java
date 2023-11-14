package dev.ganeshpc.productservice.dtos;

import dev.ganeshpc.productservice.models.Category;
import dev.ganeshpc.productservice.models.Price;
import dev.ganeshpc.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GenericProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public String toString() {
        return "Title: " + title + "  Description: " + description + "  Image: " + image + "  Category: " + category
                + "  Price: " + price;
    }

    public Product toProduct() {
        Product.ProductBuilder productBuilder = Product.builder();
        productBuilder.title(title).description(description).image(image).category(new Category(category))
                .price(new Price(price));
        Product product = productBuilder.build();
        product.setId(id);
        return product;
    }
}
