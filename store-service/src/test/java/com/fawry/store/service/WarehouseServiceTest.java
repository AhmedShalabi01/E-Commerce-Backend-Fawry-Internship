package com.fawry.store.service;

import com.fawry.store.dtos.WarehouseDto;
import com.fawry.store.entites.Warehouse;
import com.fawry.store.exceptions.NoSuchEntityException;
import com.fawry.store.repos.WarehouseRepo;
import com.fawry.store.services.WarehouseService;
import com.fawry.store.services.mapper.WarehouseMapper;
import com.fawry.store.services.serviceimp.WarehouseServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {

    @Mock
    WarehouseRepo repo;

    @Mock
    WarehouseMapper mapper;

    @InjectMocks
    WarehouseService service = new WarehouseServiceImp();

   final String WAREHOUSE_NOT_FOUND = "WAREHOUSE_NOT_FOUND";


    @Test
    void testAllWarehouses(){
        // arrange
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setId(1);
        warehouseDto.setName("name");
        warehouseDto.setLocation("location");
        Warehouse warehouse = new Warehouse(1 , "name" , "location");

        when(repo.findAll()).thenReturn(List.of(warehouse));
        when(mapper.toWarehouseDto(warehouse)).thenReturn(warehouseDto);
        // act
        List<WarehouseDto> Warehouses =  service.getAllWarehouses();
        // assert
        Assertions.assertThat(Warehouses).isNotNull().hasSize(1);
    }

    @Test
    void testGetWarehouseById(){
        // arrange
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setId(1);
        warehouseDto.setName("name");
        warehouseDto.setLocation("location");
        Warehouse warehouse = new Warehouse(1 , "name" , "location");
        when(repo.findById((long)1)).thenReturn(Optional.of(warehouse));
        when(mapper.toWarehouseDto(warehouse)).thenReturn(warehouseDto);
        // act
        WarehouseDto dto = service.getWarehouse(1);
        // assert
        assertThat(dto).isNotNull().isEqualTo(warehouseDto);
    }

    @Test
    void testGetWarehouseByIdAndNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.getWarehouse(1))
                .isInstanceOf(NoSuchEntityException.class)
                .hasMessage(WAREHOUSE_NOT_FOUND);
    }


    @Test
    void testCreateNewWarehouse(){
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setId(1);
        warehouseDto.setName("name");
        warehouseDto.setLocation("location");
        Warehouse warehouse = new Warehouse(1 , "name" , "location");

        when(repo.save(warehouse)).thenReturn(warehouse);
        when(mapper.toWarehouseDto(warehouse)).thenReturn(warehouseDto);
        when(mapper.toWarehouse(warehouseDto)).thenReturn(warehouse);
        // act
        WarehouseDto dto = service.createWarehouse(warehouseDto);
        // assert
        assertThat(dto).isNotNull().isEqualTo(warehouseDto);
    }

    @Test
    void testUpdateWarehouseAndWarehouseNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.updateWarehouse(1 , any(WarehouseDto.class)))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(WAREHOUSE_NOT_FOUND);
    }

    @Test
    void testUpdateWarehouse(){
        // arrange
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setId(1);
        warehouseDto.setName("name");
        warehouseDto.setLocation("location");
        Warehouse warehouse = new Warehouse(1 , "name" , "location");
        when(repo.findById((long)1)).thenReturn(Optional.ofNullable(warehouse));
        when(mapper.toWarehouseDto(warehouse)).thenReturn(warehouseDto);
        when(repo.save(warehouse)).thenReturn(warehouse);

        // act
        WarehouseDto dto = service.updateWarehouse(1 , warehouseDto);
        // assert
        assertThat(dto).isNotNull().isEqualTo(warehouseDto);
    }

    @Test
    void testDeleteWarehouseAndNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.removeWarehouse(1))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(WAREHOUSE_NOT_FOUND);
    }

    @Test
    void testDeleteWarehouse(){
        // arrange
        Warehouse warehouse = new Warehouse(1 , "name" , "location");
        when(repo.findById((long)1)).thenReturn(Optional.of(warehouse));
        // act
        org.junit.jupiter.api.Assertions.assertAll(() -> service.removeWarehouse(1));
    }
}
