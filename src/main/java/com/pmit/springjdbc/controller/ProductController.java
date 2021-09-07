package com.pmit.springjdbc.controller;

import com.pmit.springjdbc.dto.ListOfCategoryId;
import com.pmit.springjdbc.dto.Product;
import com.pmit.springjdbc.dto.ProductsDto;
import com.pmit.springjdbc.dto.Response;
import com.pmit.springjdbc.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    final
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/products/{name}")
    public Response<String> addProduct(@PathVariable String name) {
        return productService.addProduct(name);
    }


    @PutMapping("/api/products")
    public Response<String> updateProduct(String name, String newName) {
        return productService.updateProduct(name, newName);
    }

    @DeleteMapping("/api/products/{name}")
    public Response<String> deleteProduct(@PathVariable String name) {
        return productService.deleteProduct(name);
    }

    @GetMapping("/api/products/{name}")
    public Response<Product> viewProduct(@PathVariable String name) {
        return productService.viewProduct(name);
    }

    @GetMapping("/api/products")
    public Response<List<ProductsDto>> getAllProducts() {
        return productService.getAllProductsWithCategory();
    }

    @GetMapping("/api/products/categories")
    public Response<List<Product>> getAllProductsForGivenCategory(@RequestBody  ListOfCategoryId list) {
        System.out.println(list);
        List<Integer> ids=list.getIds();
        System.out.println(ids);
        return productService.getAllProductsForCategory(ids);
    }

    @GetMapping("/api/products/")
    public List<Product> getPagination(@RequestParam int p) {
        System.out.println("In");
        return productService.getProducts(p,5);
    }

    @GetMapping("/api/totalProducts")
    public long getProducts() {
        return productService.getTotalNumberProducts();
    }
}
