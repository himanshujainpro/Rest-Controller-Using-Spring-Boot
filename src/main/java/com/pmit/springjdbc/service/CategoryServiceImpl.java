package com.pmit.springjdbc.service;

import com.pmit.springjdbc.dto.Category;
import com.pmit.springjdbc.dto.Response;
import com.pmit.springjdbc.exception.DataNotExistException;
import com.pmit.springjdbc.exception.DataNotValidException;
import com.pmit.springjdbc.exception.DuplicateEntityException;
import com.pmit.springjdbc.repository.CategoryRepository;

import org.springframework.stereotype.Component;

import java.util.List;

import static com.pmit.springjdbc.dto.Response.ok;

@Component
public class CategoryServiceImpl implements CategoryService {

    final
    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Response<String> addCategory(String categoryName) {
        if (categoryName != null) {
            Response<String> response;
            Category category;
            category = categoryRepository.findCategoryByName(categoryName);
            if (category == null) {
                response = ok();
                response.setPayload(categoryRepository.addCategory(categoryName));
                response.setMessage("Added Successfully");
            } else {
                throw new DuplicateEntityException("Category which you are trying to add is already exits!");
            }

            return response;
        } else throw new DataNotValidException("Please give valid category name");
    }

    @Override
    public Response<String> updateCategory(String categoryName, String categoryNewName) {
        if (categoryName != null || categoryNewName != null) {
            Response<String> response;
            Category category;
            category = categoryRepository.findCategoryByName(categoryName);

            if (category == null) {
                throw new DataNotExistException("Data not Exist");
            } else if (categoryRepository.findCategoryByName(categoryNewName) == null) {
                response = ok();
                response.setPayload(categoryRepository.updateCategory(categoryName, categoryNewName));
                response.setMessage("Update Success");
                return response;
            } else {
                throw new DuplicateEntityException("Duplicate Entry");
            }
        } else throw new DataNotValidException("Please give request data");

    }

    @Override
    public Response<String> deleteCategory(String categoryName) {
        if (categoryName != null) {
            Response<String> response;
            Category category;
            category = categoryRepository.findCategoryByName(categoryName);
            if (category == null) {
                throw new DataNotExistException("Data not Exist");
            } else {
                response = ok();
                response.setPayload(categoryRepository.deleteCategory(categoryName));
                response.setMessage("Delete Success");
                return response;
            }
        } else throw new DataNotValidException("Please give valid category name");

    }

    @Override
    public Response<Category> viewCategory(String name) {
        if (name != null) {
            Category category = categoryRepository.findCategoryByName(name);
            Response<Category> response;
            if (category == null) {
                throw new DataNotExistException("Data not Exist");
            } else {
                response = ok();
                response.setPayload(category);
                response.setMessage("Success");
                return response;
            }
        } else throw new DataNotValidException("Please give valid category name");

    }

    @Override
    public Response<List<Category>> getAllCategories() {
        Response<List<Category>> response;
        List<Category> categories = categoryRepository.getAllCategories();

        if (categories == null) {
            response = Response.notFound();
            response.setMessage("Data not available");
        } else {
            response = Response.ok();
            response.setPayload(categories);
            if (categories.isEmpty()) response.setMessage("No Category Available to Display");
            else response.setMessage("Success");
        }
        return response;
    }
}
