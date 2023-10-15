package dev.ganeshpc.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ganeshpc.productservice.models.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {

}
