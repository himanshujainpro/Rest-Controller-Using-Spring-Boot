package com.pmit.springjdbc.service;

import com.pmit.springjdbc.dto.Product;


import com.pmit.springjdbc.dto.ProductsDto;
import com.pmit.springjdbc.dto.Response;
import com.pmit.springjdbc.exception.DataNotExistException;
import com.pmit.springjdbc.exception.DataNotValidException;
import com.pmit.springjdbc.exception.DuplicateEntityException;
import com.pmit.springjdbc.repository.ProductCategoryRepository;
import com.pmit.springjdbc.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public Response<String> addProduct(String productName){

        if(productName!=null){
            Product product;
            product = productRepository.findProductByName(productName);
            if (product == null) {
                Response<String> response;
                response=Response.ok();
                response.setPayload(productRepository.addProduct(productName));
                response.setMessage("Product Created Successfully");
                return response;
            } else {
                throw new DuplicateEntityException("Duplicate Entry");
            }
        }else throw new DataNotValidException("Please give valid request data / product name is null");

    }

    @Override
    public Response<String> updateProduct(String productName, String productNewName) {

        if(productName!=null || productNewName!=null){
            Product product;
            product = productRepository.findProductByName(productName);

            if(product ==null){
                throw new DataNotExistException("Data Not Exist");
            }else if(productRepository.findProductByName(productNewName)==null){
                Response<String> response;
                response=Response.ok();
                response.setPayload(productRepository.updateProduct(productName,productNewName));
                response.setMessage("Product Updated Successfully");
                return response;
            }else{
                throw new DuplicateEntityException("Duplicate Entry");
            }
        }else throw new DataNotValidException("Please give valid request data / product name is null");

    }

    @Override
    public Response<String> deleteProduct(String productName){
        if(productName!=null){
            Product product;
            product = productRepository.findProductByName(productName);
            if (product == null) {
                throw new DataNotExistException("Data Not Exist");
            }
            else {
                Response<String> response;
                response=Response.ok();
                response.setPayload(productRepository.deleteProduct(productName));
                response.setMessage("Product Deleted Successfully");
                return response;
            }
        }else throw new DataNotValidException("Please give valid request data / product name is null");

    }

    @Override
    public Response<Product> viewProduct(String name)  {
        if(name!=null){
            Product product =productRepository.findProductByName(name);
            if(product ==null){
                throw new DataNotExistException("Data Not Exist");
            }else{
                Response<Product> response;
                response=Response.ok();
                response.setPayload(product);
                response.setMessage("Success");
                return response;
            }
        }else throw new DataNotValidException("Please give valid request data / product name is null");

    }

    private Response<List<Product>> getAllProducts(){
        if(productRepository.getAllProducts()==null)
            throw new DataNotExistException("Data Not Available");
        else{
            Response<List<Product>> response;
            response=Response.ok();
            response.setPayload(productRepository.getAllProducts());
            response.setMessage("Success");
            return response;
        }
    }

    @Override
    public Response<List<ProductsDto>> getAllProductsWithCategory() {
        List<Product> products =getAllProducts().getPayload();
        List<ProductsDto> productsDtoList=new ArrayList<>();

        if(products == null) throw new DataNotExistException("No Products Are Available");
        else{
            for (Product product : products) {
                ProductsDto productsDto = new ProductsDto();
                int pid = product.getId();
                productsDto.setId(pid);
                productsDto.setName(product.getName());
                productsDto.setCategories(productCategoryRepository.listCategoriesUsingSingleProductId(pid));
                productsDtoList.add(productsDto);
            }
            Response<List<ProductsDto>> response;
            response=Response.ok();
            response.setPayload(productsDtoList);
            response.setMessage("Success");
            return response;
        }
    }

    @Override
    public Response<List<Product>> getAllProductsForCategory(List<Integer> categories){
        List<Product> products =new ArrayList<>();
        if (categories.isEmpty()) throw new DataNotValidException("Please provide valid list of categories");
        else{
            for (int id:categories
                 ) {
                List<Product> temp =productCategoryRepository.listProductUsingSingleCategoryId(id);
                        if(temp !=null) products.addAll(temp);
            }
            Response<List<Product>> response;
            response=Response.ok();
            response.setPayload(products);
            response.setMessage("Success");
            return response;
        }
    }

    @Override
    public long getTotalNumberProducts() {
        return productRepository.getTotalNumberProducts();
    }

    @Override
    public List<Product> getProducts(int pageNumber, int pageSize) {
        return productRepository.getProducts(pageNumber,pageSize);
    }
}
