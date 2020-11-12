package com.itrepka.libraryapp.service.services;

import com.itrepka.libraryapp.model.Category;
import com.itrepka.libraryapp.repository.CategoryRepository;
import com.itrepka.libraryapp.service.dto.CategoryDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateCategoryDto;
import com.itrepka.libraryapp.service.exception.AuthorAlreadyExistException;
import com.itrepka.libraryapp.service.exception.CategoryAlreadyExistException;
import com.itrepka.libraryapp.service.exception.CategoryNotFoundException;
import com.itrepka.libraryapp.service.mapper.CategoryDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryDtoMapper categoryDtoMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> categoryDtoMapper.toDto(category))
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDto getCategoryById(int id) throws CategoryNotFoundException {
        return categoryRepository.findById(id)
                .map(category -> categoryDtoMapper.toDto(category))
                .orElseThrow(() -> new CategoryNotFoundException("Not found category with id = " + id));
    }

    @Transactional
    public CategoryDto addNewCategory(CreateUpdateCategoryDto createUpdateCategoryDto) throws CategoryAlreadyExistException {
        boolean isExist = checkIsExist(createUpdateCategoryDto.getName());

        if (isExist) {
            throw new CategoryAlreadyExistException("Category already exist");
        }

        Category category = categoryDtoMapper.toModel(createUpdateCategoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryDtoMapper.toDto(savedCategory);
    }

    @Transactional
    public CategoryDto updateCategoryById(int id, CreateUpdateCategoryDto createUpdateCategoryDto) throws CategoryNotFoundException, CategoryAlreadyExistException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Not found category with id = " + id));

        boolean isExist = checkIsExist(createUpdateCategoryDto.getName());

        if (isExist) {
            throw new CategoryAlreadyExistException("Category already exist");
        }

        category.setName(createUpdateCategoryDto.getName());

        Category savedCategory = categoryRepository.save(category);
        return categoryDtoMapper.toDto(savedCategory);
    }


    public CategoryDto deleteCategoryById(int id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Not found category with id = " + id));
        categoryRepository.deleteById(id);
        return categoryDtoMapper.toDto(category);
    }

    private boolean checkIsExist(String name) {
        return getAllCategories().stream()
                .anyMatch(category -> category.getName().equalsIgnoreCase(name));
    }
}
