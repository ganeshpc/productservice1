package dev.ganeshpc.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ganeshpc.productservice.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
