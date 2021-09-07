package com.pmit.springjdbc.repository;

import com.pmit.springjdbc.dto.Category;
import com.pmit.springjdbc.service.MySqlConnectionService;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;

@Component
public interface CategoryRepository {
    String ALL_CATEGORIES_QUERY="select * from category;";
    String CATEGORY_INSERT_QUERY=
            "insert into category(name)\n" +
                    "values (?);";

    String CATEGORY_SEARCH_BY_NAME_QUERY="select * from category where\n" +
            "name=?;";

    String CATEGORY_UPDATE_QUERY="update category\n" +
            "set name=?\n" +
            "where name=?";
    String Category_DELETE_QUERY="delete from category where name=?;";

    String DELETE_CATEGORY_DATA="delete from product_category where cid=?";
    Connection connection= MySqlConnectionService.getConnection();

    String addCategory(String categoryName);
    Category findCategoryByName(String categoryName);
    String updateCategory(String categoryName,String categoryNewName);
    String deleteCategory(String categoryName);
    List<Category> getAllCategories();

}
