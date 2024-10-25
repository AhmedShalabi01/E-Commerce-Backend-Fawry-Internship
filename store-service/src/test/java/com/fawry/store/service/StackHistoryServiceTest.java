package com.fawry.store.service;

import com.fawry.store.dtos.StockHistoryDto;
import com.fawry.store.entites.StockHistory;
import com.fawry.store.exceptions.NoSuchEntityException;
import com.fawry.store.repos.StockHistoryRepo;
import com.fawry.store.services.StockHistoryService;
import com.fawry.store.services.mapper.StockHistoryMapper;
import com.fawry.store.services.serviceimp.StockHistoryServiceImp;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StackHistoryServiceTest {
    @Mock
    StockHistoryRepo repo;

    @Mock
    StockHistoryMapper mapper;

    @InjectMocks
    StockHistoryService service = new StockHistoryServiceImp();

    final String STOCK_HISTORY_NOT_FOUND = "STOCK_HISTORY_NOT_FOUND";



    @Test
    void testAllStockHistorys(){
        // arrange
        StockHistoryDto dto = new StockHistoryDto(); dto.setQuantity(50);
        StockHistory stockHistory = new StockHistory(); stockHistory.setQuantity(50);
        when(repo.findAll()).thenReturn(List.of(stockHistory));
        when(mapper.toStockHistoryDto(stockHistory)).thenReturn(dto);
        // act
        List<StockHistoryDto> histories =  service.getAllHistories();
        // assert
        Assertions.assertThat(histories).isNotNull().hasSize(1);
    }

    @Test
    void testGetStockHistoryById(){
        // arrange
        StockHistoryDto dto = new StockHistoryDto(); dto.setQuantity(50);
        StockHistory stockHistory = new StockHistory(); stockHistory.setQuantity(50);
        when(repo.findById((long)1)).thenReturn(Optional.of(stockHistory));
        when(mapper.toStockHistoryDto(stockHistory)).thenReturn(dto);
        // act
        StockHistoryDto history = service.getHistory(1);
        // assert
        assertThat(history).isNotNull().isEqualTo(dto);
    }

    @Test
    void testGetStockHistoryByIdAndNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.getHistory(1))
                .isInstanceOf(NoSuchEntityException.class)
                .hasMessage(STOCK_HISTORY_NOT_FOUND);
    }


    @Test
    void testDeleteStockHistory(){
        // arrange
        StockHistory stockHistory = new StockHistory(); stockHistory.setQuantity(50);
        when(repo.findById((long)1)).thenReturn(Optional.of(stockHistory));
        // act
        org.junit.jupiter.api.Assertions.assertAll(() -> service.removeHistory(1));
    }
}
