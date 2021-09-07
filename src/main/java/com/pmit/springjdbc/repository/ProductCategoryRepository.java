package com.pmit.springjdbc.repository;

import com.pmit.springjdbc.dto.Category;
import com.pmit.springjdbc.dto.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductCategoryRepository {
    String ADD_PID_CID_QUERY="insert into product_category values (?,?);";

    String LIST_CATEGORIES_USING_SINGLE_PRODUCT_ID="select * from category where category.id in (select product_category.cid from product_category where product_category.pid = ?);";

    String LIST_PRODUCTS_USING_SINGLE_CATEGORY_ID="select * from product where product.id in (select product_category.pid from product_category where product_category.cid=?);";
   void linkProductToCategoryBatchProcessing(int pid, List<Integer> cids);
   List<Category> listCategoriesUsingSingleProductId(int productId);
   List<Product> listProductUsingSingleCategoryId(int categoryId);
}
