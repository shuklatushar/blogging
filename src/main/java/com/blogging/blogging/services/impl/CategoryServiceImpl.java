package com.blogging.blogging.services.impl;

import com.blogging.blogging.entity.Category;
import com.blogging.blogging.exceptions.ResourceNotFoundException;
import com.blogging.blogging.payloads.CategoryDto;
import com.blogging.blogging.repositories.CategoryRepo;
import com.blogging.blogging.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category=toCategory(categoryDto);
        Category categoryResponse=categoryRepo.save(category);
        return toCategoryDto(categoryResponse);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow( ()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        Category updatedCategory=categoryRepo.save(category);
        return toCategoryDto(updatedCategory);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow( ()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        return toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
          List<Category> categories=categoryRepo.findAll();
          return categories.stream().map((category)->this.toCategoryDto(category)).collect(Collectors.toList());

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow( ()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        categoryRepo.deleteById(categoryId);


    }
    public CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDescription(category.getCategoryDescription());

        return categoryDto;
    }
    public Category toCategory(CategoryDto categoryDto){

        Category category=new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        return category;
    }

}
