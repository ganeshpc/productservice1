package dev.ganeshpc.productservice.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Category extends BaseModel {
    private String title;
}
