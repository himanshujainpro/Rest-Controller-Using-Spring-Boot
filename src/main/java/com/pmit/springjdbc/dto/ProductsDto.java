package com.pmit.springjdbc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductsDto {
    private int id;
    private String name;
    private List<Category> categories;
}
