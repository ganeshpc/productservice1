package dev.ganeshpc.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Price extends BaseModel {

    private String currency;
    private double price;
    
    public Price(double price) {
        this.currency = "INR";
        this.price = price;
    }
}
