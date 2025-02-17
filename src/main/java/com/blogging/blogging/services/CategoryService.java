package com.blogging.blogging.services;

import com.blogging.blogging.entity.Category;
import java.util.List;
import com.blogging.blogging.payloads.CategoryDto;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    CategoryDto getCategory(Integer categoryId);
    List<CategoryDto> getAllCategory();
    void deleteCategory(Integer categoryId);
}
