package com.pmit.springjdbc.repository;

import com.pmit.springjdbc.dto.Category;
import com.pmit.springjdbc.dto.Product;
import com.pmit.springjdbc.exception.DBAccessException;
import com.pmit.springjdbc.service.MySqlConnectionService;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository{

    private final static Connection connection=MySqlConnectionService.getConnection();
    @Override
    public void linkProductToCategoryBatchProcessing(int pid, List<Integer> cids){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(ADD_PID_CID_QUERY);
            for (int cid:cids
                 ) {
                preparedStatement.setInt(1,pid);
                preparedStatement.setInt(2,cid);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }catch (SQLException e){
            throw new DBAccessException(e.getMessage());
        }
    }

    @Override
    public List<Category> listCategoriesUsingSingleProductId(int productId){

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(LIST_CATEGORIES_USING_SINGLE_PRODUCT_ID);
            preparedStatement.setInt(1,productId);
            preparedStatement.execute();
            ResultSet resultSet=preparedStatement.getResultSet();
            List<Category> categories =new ArrayList<>();
            System.out.println(resultSet);
//        if(!resultSet.isAfterLast()) return null;
//        else{
            while (resultSet.next()){
                Category category =new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                categories.add(category);
            }
            //}
            if(!categories.isEmpty()) return categories;
            else return null;
        }catch (SQLException e){
            throw new DBAccessException(e.getMessage());
        }

    }

    @Override
    public List<Product> listProductUsingSingleCategoryId(int categoryId) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(LIST_PRODUCTS_USING_SINGLE_CATEGORY_ID);
            preparedStatement.setInt(1,categoryId);
            preparedStatement.execute();
            ResultSet resultSet=preparedStatement.getResultSet();
            List<Product> products =new ArrayList<>();
//        if(!resultSet.isAfterLast()) return null;
//        else{
            while (resultSet.next()){
                Product product =new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                products.add(product);
            }
            //}
            if(!products.isEmpty()) return products;
            else return null;
        }catch (SQLException e){
            throw new DBAccessException(e.getMessage());
        }
    }
}
