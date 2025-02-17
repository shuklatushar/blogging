package com.blogging.blogging.controller;

import com.blogging.blogging.payloads.ApiResponse;
import com.blogging.blogging.payloads.CategoryDto;
import com.blogging.blogging.services.CategoryService;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    //post create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategoryDto=categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(updatedCategoryDto, HttpStatus.CREATED);
    }

    //put update
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable(name = "id") Integer categoryId,@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategoryDto=categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(updatedCategoryDto,HttpStatus.OK);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") Integer categoryId){
        CategoryDto categoryDto=categoryService.getCategory(categoryId);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }

    // get all
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDto=categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(categoryDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
           categoryService.deleteCategory(id);
           return new ResponseEntity<ApiResponse>(new ApiResponse("successfully deleted category",true),HttpStatus.OK);
    }


}
