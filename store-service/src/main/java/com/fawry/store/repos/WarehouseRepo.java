package com.fawry.store.repos;

import com.fawry.store.entites.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepo extends JpaRepository<Warehouse , Long> {
}
