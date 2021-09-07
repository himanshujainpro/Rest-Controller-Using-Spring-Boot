package com.pmit.springjdbc.service;

import com.pmit.springjdbc.dto.Response;
import com.pmit.springjdbc.dto.ProductCategories;
import org.springframework.stereotype.Component;

@Component
public interface ProductCategoryService {
    Response<String> linkProductToCategory(ProductCategories productCategories);

}
