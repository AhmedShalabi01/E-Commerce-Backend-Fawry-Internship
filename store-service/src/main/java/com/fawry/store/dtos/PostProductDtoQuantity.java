package com.fawry.store.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostProductDtoQuantity {
    long id;
    String name;
    long quantity;
}
