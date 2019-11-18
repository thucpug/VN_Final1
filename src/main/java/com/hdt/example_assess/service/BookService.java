package com.hdt.example_assess.service;

import com.hdt.example_assess.model.BookDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService extends  BaseService<BookDTO>{
   public List<BookDTO> findAllPagable(Pageable pageable);
}
