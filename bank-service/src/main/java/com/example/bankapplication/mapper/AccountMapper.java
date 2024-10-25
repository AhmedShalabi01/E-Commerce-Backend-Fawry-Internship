package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.AccountRequest;
import com.example.bankapplication.dto.AccountResponse;
import com.example.bankapplication.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponse toDto(AccountEntity entity);
    AccountEntity toEntity(AccountRequest accountRequest);
}
