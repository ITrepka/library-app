package com.itrepka.libraryapp.controller;

import com.itrepka.libraryapp.service.dto.CategoryDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateCategoryDto;
import com.itrepka.libraryapp.service.exception.CategoryAlreadyExistException;
import com.itrepka.libraryapp.service.exception.CategoryNotFoundException;
import com.itrepka.libraryapp.service.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable int id) throws CategoryNotFoundException {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public CategoryDto addNewCategory(@RequestBody CreateUpdateCategoryDto createUpdateCategoryDto) throws CategoryAlreadyExistException {
        return categoryService.addNewCategory(createUpdateCategoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategoryById(@PathVariable int id, @RequestBody CreateUpdateCategoryDto createUpdateCategoryDto) throws CategoryNotFoundException, CategoryAlreadyExistException {
        return categoryService.updateCategoryById(id, createUpdateCategoryDto);
    }

    @DeleteMapping("/{id}")
    public CategoryDto deleteCategoryById(@PathVariable int id) throws CategoryNotFoundException {
        return categoryService.deleteCategoryById(id);
    }
}
