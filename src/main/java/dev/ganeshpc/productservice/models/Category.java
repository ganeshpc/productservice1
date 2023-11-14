package dev.ganeshpc.productservice.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Category extends BaseModel {

    public Category(String title) {
        this.title = title;
    }

    @Column
    private String title;

    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.SELECT)
    private List<Product> products = new ArrayList<>();

}
