package com.pmit.springjdbc.service;

import com.pmit.springjdbc.dto.Response;
import com.pmit.springjdbc.exception.DataNotValidException;
import com.pmit.springjdbc.dto.ProductCategories;
import com.pmit.springjdbc.repository.ProductCategoryRepository;
import org.springframework.stereotype.Component;


@Component
public class ProductCategoryServiceImpl implements ProductCategoryService{


    final
    ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public Response<String> linkProductToCategory(ProductCategories productCategories) {
        if(productCategories!=null){
            Response<String> response;
            int pid=productCategories.getPid();
            System.out.println(pid);

            int cat_length=productCategories.getCategories().size();

            if(cat_length==0) {
                response=Response.badRequest();
                response.setMessage("Must have one category to link");
            }

            else {
                productCategoryRepository.linkProductToCategoryBatchProcessing(pid,productCategories.getCategories());
                response=Response.ok();
                response.setPayload(productCategories.toString());
                response.setMessage("Linking Success");
            }
            return response;
        }else throw new DataNotValidException("Please provide valid data like {\n" +
                "\"pid\" : 4,\n" +
                "\"categories\" : [16,2,3]\n" +
                "}");

    }


}
