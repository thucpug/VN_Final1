package com.hdt.example_assess.service.Impl;

import com.hdt.example_assess.entity.Book_Bill;
import com.hdt.example_assess.model.Book_BillDTO;
import com.hdt.example_assess.respository.Book_BillRepository;
import com.hdt.example_assess.service.Book_BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Book_BillServiceImpl implements Book_BillService {
    @Autowired
    Book_BillRepository book_billRepository;
    @Override
    public Book_BillDTO add(Book_BillDTO obj) {
        return null;
    }
    public void add(Book_Bill obj) {
       book_billRepository.save(obj);
    }
    @Override
    public Book_BillDTO delete(Book_BillDTO obj) {
        return null;
    }

    @Override
    public Book_BillDTO delete(int ids) {
        return null;
    }

    @Override
    public Book_BillDTO update(Book_BillDTO obj) {
        return null;
    }

    @Override
    public Book_BillDTO save(Book_BillDTO obj) {
        return null;
    }

    @Override
    public Book_BillDTO findById(int id) {
        return null;
    }

    @Override
    public List<Book_BillDTO> findAll() {
        return null;
    }

    @Override
    public int counts() {
        return 0;
    }
}
