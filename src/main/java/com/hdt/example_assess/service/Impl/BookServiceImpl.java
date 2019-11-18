package com.hdt.example_assess.service.Impl;

import com.hdt.example_assess.converter.newConverter;
import com.hdt.example_assess.entity.Author;
import com.hdt.example_assess.entity.Book;
import com.hdt.example_assess.entity.Kind;
import com.hdt.example_assess.entity.Languages;
import com.hdt.example_assess.model.BookDTO;
import com.hdt.example_assess.respository.AuthorRepository;
import com.hdt.example_assess.respository.BookRepository;
import com.hdt.example_assess.respository.KindRepository;
import com.hdt.example_assess.respository.LanguagesRepository;
import com.hdt.example_assess.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    KindRepository kindRepository;
    @Autowired
    LanguagesRepository languagesRepository;

    @Autowired
    newConverter newConverter;

    @Override
    public BookDTO add(BookDTO obj) {
        Author author = authorRepository.findById(obj.getAuthorDTO().getId()).get();
        Kind kind = kindRepository.findById(obj.getKindDTO().getId()).get();
        Languages languages = languagesRepository.findById(obj.getLanguagesDTO().getId()).get();
        Book book = newConverter.entityBookFromDto(obj);
        book.setAuthor(author);
        book.setKind(kind);
        book.setLanguage(languages);
        System.out.println(book.toString());
        bookRepository.save(book);
        return null;
    }

    @Override
    public BookDTO delete(BookDTO obj) {
        return null;
    }

    @Override
    public BookDTO delete(int ids) {
        Book item = bookRepository.findById(ids).get();
        if (item != null) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setAuthorDTO(newConverter.dtoAuthorFromEntity(authorRepository.findById(item.getAuthor().getId()).get()));
            bookDTO.setKindDTO(newConverter.dtoKindFromEntity(kindRepository.findById(item.getKind().getId()).get()));
            bookDTO.setLanguagesDTO(newConverter.dtoLanguageFromEnity(languagesRepository.findById(item.getLanguage().getId()).get()));
            newConverter.dtoBookFromEntity(item, bookDTO);
            bookRepository.delete(item);
            return bookDTO;
        }
        return null;
    }
    @Override
    public BookDTO update(BookDTO obj) {
        Book oldBook = bookRepository.findById(obj.getId()).get();
        Book newBook = newConverter.convertBookOldtoNewBook(oldBook, obj);
        Author author = authorRepository.findById(obj.getAuthorDTO().getId()).get();
        Kind kind = kindRepository.findById(obj.getKindDTO().getId()).get();
        Languages languages = languagesRepository.findById(obj.getLanguagesDTO().getId()).get();
        newBook.setAuthor(author);
        newBook.setKind(kind);
        newBook.setLanguage(languages);

        obj.setAuthorDTO(newConverter.dtoAuthorFromEntity(author));
        obj.setKindDTO(newConverter.dtoKindFromEntity(kind));
        obj.setLanguagesDTO(newConverter.dtoLanguageFromEnity(languages));
        bookRepository.save(newBook);
        return obj;
    }

    @Override
    public BookDTO save(BookDTO obj) {
        Author author = authorRepository.findById(obj.getAuthorDTO().getId()).get();
        Kind kind = kindRepository.findById(obj.getKindDTO().getId()).get();
        Languages languages = languagesRepository.findById(obj.getLanguagesDTO().getId()).get();
        Book newBook = null;
        if (obj.getId() != 0) {
            Book oldBook = bookRepository.findById(obj.getId()).get();
            newBook = newConverter.convertBookOldtoNewBook(oldBook, obj);
        } else {
            newBook = newConverter.entityBookFromDto(obj);
        }
        newBook.setAuthor(author);
        newBook.setKind(kind);
        newBook.setLanguage(languages);
        bookRepository.save(newBook);
        return null;
    }

    @Override
    public BookDTO findById(int id) {
        Book item = bookRepository.findById(id).get();
        if (item != null) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setAuthorDTO(newConverter.dtoAuthorFromEntity(authorRepository.findById(item.getAuthor().getId()).get()));
            bookDTO.setKindDTO(newConverter.dtoKindFromEntity(kindRepository.findById(item.getKind().getId()).get()));
            bookDTO.setLanguagesDTO(newConverter.dtoLanguageFromEnity(languagesRepository.findById(item.getLanguage().getId()).get()));
            newConverter.dtoBookFromEntity(item, bookDTO);
            return bookDTO;
        }
        return null;
    }

    @Override
    public List<BookDTO> findAll() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        for (Book item : books) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setAuthorDTO(newConverter.dtoAuthorFromEntity(authorRepository.findById(item.getAuthor().getId()).get()));
            bookDTO.setKindDTO(newConverter.dtoKindFromEntity(kindRepository.findById(item.getKind().getId()).get()));
            bookDTO.setLanguagesDTO(newConverter.dtoLanguageFromEnity(languagesRepository.findById(item.getLanguage().getId()).get()));
            bookDTOS.add(newConverter.dtoBookFromEntity(item, bookDTO));
        }
        return bookDTOS;
    }

    @Override
    public int counts() {
        return (int) bookRepository.count();
    }


    @Override
    public List<BookDTO> findAllPagable(Pageable pageable) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookRepository.findAll(pageable).getContent();
        for (Book item : books) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setAuthorDTO(newConverter.dtoAuthorFromEntity(authorRepository.findById(item.getAuthor().getId()).get()));
            bookDTO.setKindDTO(newConverter.dtoKindFromEntity(kindRepository.findById(item.getKind().getId()).get()));
            bookDTO.setLanguagesDTO(newConverter.dtoLanguageFromEnity(languagesRepository.findById(item.getLanguage().getId()).get()));
            bookDTOS.add(newConverter.dtoBookFromEntity(item, bookDTO));
        }
        return bookDTOS;
    }
}
