package com.pmit.springjdbc.repository;

import com.pmit.springjdbc.dto.Product;
import com.pmit.springjdbc.service.MySqlConnectionService;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;

@Component
public interface ProductRepository {

    String ALL_PRODUCTS_QUERY="select * from product;";
    String PRODUCT_INSERT_QUERY=
            "insert into product(name)\n" +
                    "values (?);";


    String PRODUCT_SEARCH_BY_NAME_QUERY="select * from product where\n" +
            "name=?;";


    String PRODUCT_UPDATE_QUERY="update product\n" +
            "set name=?\n" +
            "where name=?";
    String PRODUCT_DELETE_QUERY="delete from product where name=?;";
    String DELETE_PRODUCT_DATA="delete from product_category where pid=?";

    String PAGINATION_QUERY="select *\n" +
            "from (select row_number() over (order by name) as rownum,name,id from product) as RowConstrainedResult\n" +
            "where rownum > ?\n" +
            "  and rownum <= ?\n" +
            "order by rownum;";

    String CALCULATE_TOTAL_NUMBER_OF_PRODUCTS="select count(*) from product;";

    Connection connection= MySqlConnectionService.getConnection();
    List<Product> getAllProducts();
    String addProduct(String productName);
    Product findProductByName(String productName);
    String updateProduct(String productName,String productNewName);
    String deleteProduct(String productName) ;
    List<Product> getProducts(int pageNumber, int pageSize);
    long getTotalNumberProducts();
}
