package com.pmit.springjdbc.repository;

import com.pmit.springjdbc.dto.Category;
import com.pmit.springjdbc.exception.DBAccessException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public String addCategory(String categoryName) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CATEGORY_INSERT_QUERY);
            preparedStatement.setString(1, categoryName);
            preparedStatement.executeUpdate();
            return "Added";
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CATEGORY_SEARCH_BY_NAME_QUERY);
            preparedStatement.setString(1, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return getCategoryDtoFromResultSet(resultSet);
            else return null;

        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }


    }

    @Override
    public String updateCategory(String categoryName, String categoryNewName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CATEGORY_UPDATE_QUERY);
            preparedStatement.setString(1, categoryNewName);
            preparedStatement.setString(2, categoryName);
            preparedStatement.executeUpdate();
            return categoryName + " Category Updated to " + categoryNewName;
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }
    }

    @Override
    public String deleteCategory(String categoryName) {

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(DELETE_CATEGORY_DATA);
            Category category = findCategoryByName(categoryName);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.executeUpdate();
            System.out.println("Category Data Deleted");
            preparedStatement = connection.prepareStatement(Category_DELETE_QUERY);
            preparedStatement.setString(1, categoryName);
            preparedStatement.executeUpdate();
            return categoryName + " Category deleted";
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }

    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ALL_CATEGORIES_QUERY);

            while (resultSet.next()) {
                categories.add(getCategoryDtoFromResultSet(resultSet));
            }

            return categories;
        } catch (SQLException e) {
            throw new DBAccessException(e.getMessage());
        }
    }

    public Category getCategoryDtoFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setName(resultSet.getString(2));
        category.setId(resultSet.getInt(1));
        return category;
    }
}
