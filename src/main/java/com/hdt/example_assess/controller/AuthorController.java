package com.hdt.example_assess.controller;

import com.hdt.example_assess.entity.Author;
import com.hdt.example_assess.model.AuthorDTO;
import com.hdt.example_assess.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping(value = "/author")
    public List<AuthorDTO> getAuthors() {
        return authorService.findAll();
    }
    @PostMapping (value = "/author")
    public AuthorDTO createAuthors(@RequestBody AuthorDTO authorDTO) {
        System.out.println(authorDTO.toString());
        return authorService.add(authorDTO);
    }
}
