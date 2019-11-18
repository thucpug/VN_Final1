package com.hdt.example_assess.service.Impl;

import com.hdt.example_assess.converter.newConverter;
import com.hdt.example_assess.entity.Author;
import com.hdt.example_assess.model.AuthorDTO;
import com.hdt.example_assess.respository.AuthorRepository;
import com.hdt.example_assess.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    newConverter newConverter;

    @Override
    public AuthorDTO add(AuthorDTO obj) {
        AuthorDTO authorDTO = new AuthorDTO();
        Author author = authorRepository.save(newConverter.entityAuthorFromDto(obj));
        return  newConverter.dtoAuthorFromEntity(author);
    }

    @Override
    public AuthorDTO delete(AuthorDTO obj) {
        return null;
    }

    @Override
    public AuthorDTO delete(int ids) {
        return null;
    }

    @Override
    public AuthorDTO update(AuthorDTO obj) {
        return null;
    }

    @Override
    public AuthorDTO save(AuthorDTO obj) {
        return null;
    }

    @Override
    public AuthorDTO findById(int id) {
        AuthorDTO authorDTO = new AuthorDTO();
        Author author = authorRepository.findById(id).get();
        return newConverter.dtoAuthorFromEntity(author);

    }

    @Override
    public List<AuthorDTO> findAll() {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        List<Author> lAuthor = authorRepository.findAll();
        for (Author item : lAuthor) {
            authorDTOS.add(newConverter.dtoAuthorFromEntity(item));
        }
        return authorDTOS;
    }

    @Override
    public int counts() {
        return (int) authorRepository.count();
    }
    @Override
    public AuthorDTO findByNameAuthor(String name){
       Author author = authorRepository.findOneByAuthorName(name);
       return newConverter.dtoAuthorFromEntity(author);
    }
}
