package dev.ganeshpc.productservice.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "orders")
@Getter
@Setter
public class Order extends BaseModel {

    @ManyToMany
    private List<Product> products;
    private double quantity;
}
