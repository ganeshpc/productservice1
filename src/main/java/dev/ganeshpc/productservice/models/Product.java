package dev.ganeshpc.productservice.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
}
