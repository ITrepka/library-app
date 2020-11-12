package com.itrepka.libraryapp.service.mapper;

import com.itrepka.libraryapp.model.Book;
import com.itrepka.libraryapp.model.Category;
import com.itrepka.libraryapp.repository.BookRepository;
import com.itrepka.libraryapp.service.dto.CategoryDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryDtoMapper {
    public CategoryDto toDto(Category category) {

        List<Long> booksIds = category.getBooks() == null ? null :
                category.getBooks().stream().map(Book::getBookId).collect(Collectors.toList());

        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .booksIds(booksIds)
                .build();
    }

    public Category toModel(CreateUpdateCategoryDto createUpdateCategoryDto) {
        return Category.builder()
                .categoryId(null)
                .name(createUpdateCategoryDto.getName())
                .books(new ArrayList<>())
                .build();
    }
}
