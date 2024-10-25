package com.example.orderapi.dtos;

import lombok.Data;

@Data
public class DummyDto {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
