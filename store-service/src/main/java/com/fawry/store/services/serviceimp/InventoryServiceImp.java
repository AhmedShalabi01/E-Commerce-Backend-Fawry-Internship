package com.fawry.store.services.serviceimp;

import com.fawry.store.dtos.InventoryDto;
import com.fawry.store.dtos.ProductDto;
import com.fawry.store.entites.Inventory;
import com.fawry.store.exceptions.NoSuchEntityException;
import com.fawry.store.repos.InventoryRepo;
import com.fawry.store.services.InventoryService;
import com.fawry.store.services.mapper.InventoryMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Primary
public class InventoryServiceImp implements InventoryService {

    InventoryRepo repo;
    InventoryMapper mapper;


    @Autowired
    public InventoryServiceImp(InventoryRepo repo , InventoryMapper mapper){
        this.repo = repo;
        this.mapper = mapper;
    }


    final String INVENTORY_NOT_FOUND = "INVENTORY_NOT_FOUND";
    @Override
    public List<InventoryDto> getAllInventories() {
        return repo.findAll()
                .stream()
                .map(mapper::toInventoryDto)
                .toList();
    }

    @SneakyThrows
    @Override
    public InventoryDto getInventoryById(long id) {
        return mapper.toInventoryDto(repo.findById(id)
                .orElseThrow(()-> new NoSuchEntityException(INVENTORY_NOT_FOUND)));
    }

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        inventoryDto.setDate(new Date());
        return mapper.toInventoryDto(repo.save(mapper.toInventory(inventoryDto)));
    }

    @Override
    public InventoryDto updateInventory(long id, InventoryDto inventoryDto) {
        Inventory inventory = repo.findById(id).orElseThrow(()-> new NoSuchEntityException(INVENTORY_NOT_FOUND));

        inventory.setProductQuantity(inventoryDto.getProductQuantity());

        return mapper.toInventoryDto(repo.save(inventory));
    }

    @Override
    public void destroyInventory(long id) {
        Inventory inventory = repo.findById(id).orElseThrow(() -> new NoSuchEntityException(INVENTORY_NOT_FOUND));
        repo.delete(inventory);
    }



}
