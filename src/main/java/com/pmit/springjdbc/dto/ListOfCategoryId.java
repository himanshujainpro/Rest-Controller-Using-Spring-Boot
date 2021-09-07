package com.pmit.springjdbc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListOfCategoryId {
    List<Integer> ids;
}
