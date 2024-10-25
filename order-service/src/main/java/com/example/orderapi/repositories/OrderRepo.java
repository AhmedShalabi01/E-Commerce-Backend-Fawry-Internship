package com.example.orderapi.repositories;

import com.example.orderapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Integer>{
    Optional<List<Order>> findAllByEmail(String email);
    Optional<List<Order>> findByEmail(String email);
    Optional<List<Order>> findByDateBetween(LocalDate startDate, LocalDate endDate);
    Optional<List<Order>> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
    Optional<List<Order>> findByEmailAndDateBetween(String email, LocalDate startDate, LocalDate endDate);

}
