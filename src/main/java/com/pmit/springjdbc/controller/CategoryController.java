package com.pmit.springjdbc.controller;


import com.pmit.springjdbc.dto.Category;

import com.pmit.springjdbc.dto.Response;
import com.pmit.springjdbc.service.CategoryService;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class CategoryController {

    final
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/api/categories/{name}")
    public Response<String> addCategory(@PathVariable String name) {
        return categoryService.addCategory(name);
    }


    @PutMapping("/api/categories")
    public Response<String> updateCategory(String name, String newName) {
        return categoryService.updateCategory(name, newName);
    }

    @DeleteMapping("/api/categories/{name}")
    public Response<String> deleteCategory(@PathVariable String name) {
        return categoryService.deleteCategory(name);
    }

    @GetMapping("/api/categories/{name}")
    public Response<Category> viewCategory(@PathVariable String name) {
        return categoryService.viewCategory(name);
    }

    @GetMapping("/api/categories")
    public Response<List<Category>> getAllProducts() {
        return categoryService.getAllCategories();
    }

}
