package com.pmit.springjdbc.controller;


import com.pmit.springjdbc.dto.Response;
import com.pmit.springjdbc.dto.ProductCategories;
import com.pmit.springjdbc.service.ProductCategoryService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCategoryController {

    final
    ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PutMapping("/api/products/categories")
    public Response<String> linkProductToCategory(@RequestBody ProductCategories productCategories) {
        return productCategoryService.linkProductToCategory(productCategories);
    }
}
