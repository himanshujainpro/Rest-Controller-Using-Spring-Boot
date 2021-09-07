package com.pmit.springjdbc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class ProductCategories {
    private int pid;
    private List<Integer> categories;
}
