package dev.ganeshpc.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ganeshpc.productservice.models.Category;

public class CategoryRepository  extends JpaRepository<Category, Long> {
    
}
