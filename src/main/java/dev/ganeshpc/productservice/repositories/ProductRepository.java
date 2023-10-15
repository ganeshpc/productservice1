package dev.ganeshpc.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ganeshpc.productservice.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
