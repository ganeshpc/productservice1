package dev.ganeshpc.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericProductDto {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
