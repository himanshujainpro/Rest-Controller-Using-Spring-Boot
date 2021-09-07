package com.pmit.springjdbc.repository;

import com.pmit.springjdbc.dto.Product;
import com.pmit.springjdbc.exception.DBAccessException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class ProductRepositoryImpl implements ProductRepository {


    public Product findProductByName(String productName) {

        try {
            Product product = new Product();
            PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_SEARCH_BY_NAME_QUERY);
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) return null;
            else {
                resultSet.next();
                product.setName(resultSet.getString(2));
                product.setId(resultSet.getInt(1));
                return product;
            }

        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }

    }


    @Override
    public String updateProduct(String productName, String productNewName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_UPDATE_QUERY);
            preparedStatement.setString(1, productNewName);
            preparedStatement.setString(2, productName);
            preparedStatement.executeUpdate();
            return productName + " Product Updated to " + productNewName;
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }
    }

    @Override
    public String deleteProduct(String productName) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(DELETE_PRODUCT_DATA);
            Product product = findProductByName(productName);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
            System.out.println("Product Data Deleted");
            preparedStatement = connection.prepareStatement(PRODUCT_DELETE_QUERY);
            preparedStatement.setString(1, productName);
            preparedStatement.executeUpdate();
            return productName + " Product deleted";
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }

    }

    @Override
    public List<Product> getProducts(int pageNumber, int pageSize) {
        PreparedStatement preparedStatement;
        List<Product> products = new ArrayList<>();
        int page_start_index = (pageNumber * pageSize) - pageSize;
        int page_end_index = (pageNumber * pageSize);

        try {
            preparedStatement = connection.prepareStatement(PAGINATION_QUERY);
            preparedStatement.setInt(1, page_start_index);
            preparedStatement.setInt(2, page_end_index);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString(2));
                product.setId(resultSet.getInt(3));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }
        return products;
    }

    @Override
    public long getTotalNumberProducts() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CALCULATE_TOTAL_NUMBER_OF_PRODUCTS);
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {

        try {
            List<Product> products = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ALL_PRODUCTS_QUERY);
            if (!resultSet.isBeforeFirst()) return null;
            else {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt(1));
                    product.setName(resultSet.getString(2));
                    products.add(product);
                }
            }
            return products;
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }

    }

    @Override
    public String addProduct(String productName) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_INSERT_QUERY);
            preparedStatement.setString(1, productName);
            preparedStatement.executeUpdate();
            return productName + " Product added";
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }

    }
}
