package dev.ganeshpc.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ganeshpc.productservice.models.Price;

public interface CategoryRepository extends JpaRepository<Price, Long> {

}
