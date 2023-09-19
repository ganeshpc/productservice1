package dev.ganeshpc.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
}
