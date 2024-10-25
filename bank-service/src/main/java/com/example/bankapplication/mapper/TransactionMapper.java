package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.TransactionResponse;
import com.example.bankapplication.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toDTO(TransactionEntity transactionEntity);
}
