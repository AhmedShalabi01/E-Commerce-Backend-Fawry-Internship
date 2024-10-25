package com.fawry.store.service;

import com.fawry.store.dtos.InventoryDto;
import com.fawry.store.entites.Inventory;
import com.fawry.store.exceptions.NoSuchEntityException;
import com.fawry.store.repos.InventoryRepo;
import com.fawry.store.services.InventoryService;
import com.fawry.store.services.mapper.InventoryMapper;
import com.fawry.store.services.serviceimp.InventoryServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @Mock
    InventoryRepo repo;

    @Mock
    InventoryMapper mapper;

    @InjectMocks
    InventoryService service = new InventoryServiceImp(repo , mapper);

    final String INVENTORY_NOT_FOUND = "INVENTORY_NOT_FOUND";

    @Test
    void testAllInventories(){
        // arrange
        InventoryDto inventoryDto = new InventoryDto();inventoryDto.setProductQuantity(5);
        Inventory inventory = new Inventory(5);

        when(repo.findAll()).thenReturn(List.of(inventory));
        when(mapper.toInventoryDto(inventory)).thenReturn(inventoryDto);
        // act
        List<InventoryDto> inventories =  service.getAllInventories();
        // assert
        Assertions.assertThat(inventories).isNotNull().hasSize(1);
    }

    @Test
    void testGetInventoryById(){
        // arrange
        InventoryDto inventoryDto = new InventoryDto();inventoryDto.setProductQuantity(5);
        Inventory inventory = new Inventory(5);
        when(repo.findById((long)1)).thenReturn(Optional.of(inventory));
        when(mapper.toInventoryDto(inventory)).thenReturn(inventoryDto);
        // act
        InventoryDto dto = service.getInventoryById(1);
        // assert
        assertThat(dto).isNotNull().isEqualTo(inventoryDto);
    }

    @Test
    void testGetInventoryByIdAndNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.getInventoryById(1))
                .isInstanceOf(NoSuchEntityException.class)
                .hasMessage(INVENTORY_NOT_FOUND);
    }


    @Test
    void testCreateNewInventory(){
        InventoryDto inventoryDto = new InventoryDto();inventoryDto.setProductQuantity(5);
        Inventory inventory = new Inventory(5);

        when(repo.save(inventory)).thenReturn(inventory);
        when(mapper.toInventoryDto(inventory)).thenReturn(inventoryDto);
        when(mapper.toInventory(inventoryDto)).thenReturn(inventory);
        // act
        InventoryDto dto = service.createInventory(inventoryDto);
        // assert
        assertThat(dto).isNotNull().isEqualTo(inventoryDto);
    }

    @Test
    void testUpdateInventoryAndInventoryNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.updateInventory(1 , any(InventoryDto.class)))
                .isInstanceOf(NoSuchEntityException.class)
                .hasMessage(INVENTORY_NOT_FOUND);
    }

    @Test
    void testUpdateInventory(){
        // arrange
        InventoryDto inventoryDto = new InventoryDto();inventoryDto.setProductQuantity(5);
        Inventory inventory = new Inventory(5);
        when(repo.findById((long)1)).thenReturn(Optional.ofNullable(inventory));
        when(mapper.toInventoryDto(inventory)).thenReturn(inventoryDto);
        when(repo.save(inventory)).thenReturn(inventory);

        // act
        InventoryDto dto = service.updateInventory(1 , inventoryDto);
        // assert
        assertThat(dto).isNotNull().isEqualTo(inventoryDto);
    }

    @Test
    void testDeleteInventoryAndNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.destroyInventory(1))
                .isInstanceOf(NoSuchEntityException.class)
                .hasMessage(INVENTORY_NOT_FOUND);
    }

    @Test
    void testDeleteInventory(){
        // arrange
        Inventory inventory = new Inventory(5);
        when(repo.findById((long)1)).thenReturn(Optional.of(inventory));
        // act
        org.junit.jupiter.api.Assertions.assertAll(() -> service.destroyInventory(1));
    }
}
