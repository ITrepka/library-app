package com.itrepka.libraryapp.service.services;

import com.itrepka.libraryapp.model.Author;
import com.itrepka.libraryapp.repository.AuthorRepository;
import com.itrepka.libraryapp.service.dto.AuthorDto;
import com.itrepka.libraryapp.service.dto.CreateUpdateAuthorDto;
import com.itrepka.libraryapp.service.exception.AuthorNotFoundException;
import com.itrepka.libraryapp.service.mapper.AuthorDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorDtoMapper authorDtoMapper;

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> authorDtoMapper.toDto(author))
                .collect(Collectors.toList());
    }

    @Transactional
    public AuthorDto getAuthorById(long id) throws AuthorNotFoundException {
        return authorRepository.findById(id)
                .map(author -> authorDtoMapper.toDto(author))
                .orElseThrow(() -> new AuthorNotFoundException("Not found author with id = " + id));
    }

    @Transactional
    public AuthorDto addNewAuthor(CreateUpdateAuthorDto createUpdateAuthorDto) {
        Author author = authorDtoMapper.toModel(createUpdateAuthorDto);
        Author savedAuthor = authorRepository.save(author);
        return authorDtoMapper.toDto(savedAuthor);
    }

    @Transactional
    public AuthorDto updateAuthorById(long id, CreateUpdateAuthorDto createUpdateAuthorDto) throws AuthorNotFoundException {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Not found author with id = " + id));

        author.setName(createUpdateAuthorDto.getName());
        author.setSurname(createUpdateAuthorDto.getSurname());
        author.setBirthplace(createUpdateAuthorDto.getBirthplace());
        author.setBirthYear(createUpdateAuthorDto.getBirthYear());
        author.setDeathYear(createUpdateAuthorDto.getDeathYear());
        author.setNickname(createUpdateAuthorDto.getNickname());

        Author savedAuthor = authorRepository.save(author);
        return authorDtoMapper.toDto(savedAuthor);
    }


    public AuthorDto deleteAuthorById(long id) throws AuthorNotFoundException {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Not found author with id = " + id));
        authorRepository.deleteById(id);
        return authorDtoMapper.toDto(author);
    }
}
