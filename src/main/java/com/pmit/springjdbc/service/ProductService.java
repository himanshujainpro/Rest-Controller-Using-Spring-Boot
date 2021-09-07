package com.pmit.springjdbc.service;

import com.pmit.springjdbc.dto.Product;
import com.pmit.springjdbc.dto.ProductsDto;
import com.pmit.springjdbc.dto.Response;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ProductService {

    Response<String> addProduct(String productName);

    Response<String> updateProduct(String productName, String productNewName);

    Response<String> deleteProduct(String productName);

    Response<Product> viewProduct(String name);

    Response<List<ProductsDto>> getAllProductsWithCategory();

    Response<List<Product>> getAllProductsForCategory(List<Integer> categories);

    long getTotalNumberProducts();
    List<Product> getProducts(int pageNumber, int pageSize);
}
