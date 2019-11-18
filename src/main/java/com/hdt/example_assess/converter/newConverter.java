package com.hdt.example_assess.converter;

import com.hdt.example_assess.entity.*;
import com.hdt.example_assess.model.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class newConverter {
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    //String date = simpleDateFormat.format(new Date());

    //Entity
    public Book entityBookFromDto(BookDTO dto) {
        Book book = new Book();
        book.setContentsBook(dto.getContentsBook());
        book.setImage(dto.getImage());
        book.setNameBook(dto.getNameBook());
        book.setPapers(dto.getPapers());
        book.setStatus(dto.isStatus());
        book.setAmounts(dto.getAmounts());
        return book;
    }

    public Author entityAuthorFromDto(AuthorDTO dto) {
        Author author = new Author();
        author.setAuthorName(dto.getAuthorName());
        return author;
    }

    public Book convertBookOldtoNewBook(Book oldBook, BookDTO dto) {
        oldBook.setContentsBook(dto.getContentsBook());
        oldBook.setImage(dto.getImage());
        oldBook.setNameBook(dto.getNameBook());
        oldBook.setPapers(dto.getPapers());
        oldBook.setStatus(dto.isStatus());
        oldBook.setAmounts(dto.getAmounts());
        oldBook.setCreateDate(dto.getCreateDate());
        return oldBook;
    }

    //DTO
    public AuthorDTO dtoAuthorFromEntity(Author entity) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(entity.getId());
        authorDTO.setAuthorName(entity.getAuthorName());
        authorDTO.setCreateDate(entity.getCreateDate());
        return authorDTO;
    }

    public KindDTO dtoKindFromEntity(Kind entity) {
        KindDTO kindDTO = new KindDTO();
        kindDTO.setId(entity.getId());
        kindDTO.setKindName(entity.getKindName());
        kindDTO.setCreateDate(entity.getCreateDate());
        return kindDTO;
    }

    public LanguagesDTO dtoLanguageFromEnity(Languages enity) {
        LanguagesDTO languagesDTO = new LanguagesDTO();
        languagesDTO.setId(enity.getId());
        languagesDTO.setLanguage(enity.getLanguage());
        languagesDTO.setCreateDate(enity.getCreateDate());
        return languagesDTO;
    }

    public BookDTO dtoBookFromEntity(Book entity, BookDTO bookDTO) {
        bookDTO.setId(entity.getId());
        bookDTO.setNameBook(entity.getNameBook());
        bookDTO.setImage(entity.getImage());
        bookDTO.setPapers(entity.getPapers());
        bookDTO.setStatus(entity.isStatus());
        bookDTO.setCreateDate(entity.getCreateDate());
        bookDTO.setContentsBook(entity.getContentsBook());
        bookDTO.setAmounts(entity.getAmounts());
        return bookDTO;
    }

    public BillDTO dtoBillFromEntity(Bill entity) {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(entity.getId());
        billDTO.setNamePerson(entity.getNamePerson());
        billDTO.setContentsBill(entity.getContentsBill());
        billDTO.setCreateDate(entity.getCreateDate());
        billDTO.setCreateDateEnded(entity.getCreateDateEnded());
        billDTO.setStatus(entity.isStatus());
        for (Book_Bill item : entity.getBooks()) {
            Book_BillDTO book_billDTO = new Book_BillDTO();
            book_billDTO.setId(item.getId());
            book_billDTO.setCounts(item.getCounts());
            book_billDTO.setBill_id(item.getBill().getId());
            book_billDTO.setBillCustomerName(item.getBill().getNamePerson());
            book_billDTO.setBookName(item.getBook().getNameBook());
            book_billDTO.setBook_id(item.getBook().getId());
            billDTO.getBook_billDTOS().add(book_billDTO);
        }
        return billDTO;
    }
    public void dtoBillReportFromEntity(Bill entity, List<BillReportDTO> billReportDTOS) {
        if (entity.getBooks().size() > 1) {
            for (int i = 0; i < entity.getBooks().size(); i++) {
                BillReportDTO billReportDTO = new BillReportDTO();
                billReportDTO.setId(entity.getId());
                billReportDTO.setNamePerson(entity.getNamePerson());
                billReportDTO.setCreateDate(simpleDateFormat.format(entity.getCreateDate()));
                billReportDTO.setCreateDateEnded(simpleDateFormat.format(entity.getCreateDateEnded()));
                billReportDTO.setCounts(entity.getBooks().get(i).getCounts());
                billReportDTO.setNameBook(entity.getBooks().get(i).getBook().getNameBook());
                billReportDTOS.add(billReportDTO);
            }
        } else {
            BillReportDTO billReportDTO = new BillReportDTO();
            billReportDTO.setId(entity.getId());
            billReportDTO.setNamePerson(entity.getNamePerson());
            billReportDTO.setCreateDate(simpleDateFormat.format(entity.getCreateDate()));
            billReportDTO.setCreateDateEnded(simpleDateFormat.format(entity.getCreateDateEnded()));
            billReportDTO.setCounts(entity.getBooks().get(0).getCounts());
            billReportDTO.setNameBook(entity.getBooks().get(0).getBook().getNameBook());
            billReportDTOS.add(billReportDTO);
        }


    }

    public Bill entityBillFromDto(BillDTO dto) {
        Bill bill = new Bill();
        //bill.setId(dto.getId());
        bill.setContentsBill(dto.getContentsBill());
        bill.setNamePerson(dto.getNamePerson());
        bill.setCreateDateEnded(dto.getCreateDateEnded());
        bill.setStatus(dto.isStatus());
//        for(Book_BillDTO item : dto.getBook_billDTOS()){
//            Book_Bill book_bill = new Book_Bill();
//            book_bill.setCounts(item.getCounts());
//            book_bill.getBook().setId(item.getBook_id());
//            book_bill.getBill().setId(item.getBill_id());
//            bill.setBooks();
//            //bill.getBooks().add(book_bill);
//        }
        return bill;
    }
}
