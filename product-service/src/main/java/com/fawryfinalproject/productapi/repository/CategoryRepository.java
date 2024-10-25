package com.fawryfinalproject.productapi.repository;

import com.fawryfinalproject.productapi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    Optional<CategoryEntity> findByName(String name);
}
