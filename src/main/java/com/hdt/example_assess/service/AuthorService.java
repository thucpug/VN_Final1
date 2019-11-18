package com.hdt.example_assess.service;

import com.hdt.example_assess.model.AuthorDTO;


import java.util.List;

public interface AuthorService extends  BaseService<AuthorDTO>{
 public AuthorDTO findByNameAuthor(String name);
}
