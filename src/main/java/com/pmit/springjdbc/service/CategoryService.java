package com.pmit.springjdbc.service;

import com.pmit.springjdbc.dto.Category;
import com.pmit.springjdbc.dto.Response;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    Response<String> addCategory(String categoryName);
    Response<String> updateCategory(String categoryName, String categoryNewName);
    Response<String> deleteCategory(String categoryName);
    Response<Category> viewCategory(String name);
    Response<List<Category>> getAllCategories();

}
