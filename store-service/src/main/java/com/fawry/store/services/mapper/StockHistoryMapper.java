package com.fawry.store.services.mapper;

import com.fawry.store.dtos.StockHistoryDto;
import com.fawry.store.entites.StockHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockHistoryMapper {

    StockHistoryDto toStockHistoryDto (StockHistory stockHistory);
    StockHistory toStockHistory(StockHistoryDto stockHistoryDto);
}
