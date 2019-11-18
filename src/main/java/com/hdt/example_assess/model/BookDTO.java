package com.hdt.example_assess.model;

import com.hdt.example_assess.entity.Author;
import com.hdt.example_assess.entity.Book_Bill;
import com.hdt.example_assess.entity.Kind;
import com.hdt.example_assess.entity.Languages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private int id;
    private String nameBook;
    private String contentsBook;
    private String image;
    private Date createDate;
    private long papers;
    private boolean status;
    private AuthorDTO authorDTO;
    private KindDTO kindDTO;
    private LanguagesDTO languagesDTO;
    private long amounts;
    private List<Book_BillDTO> book_billDTOS = new ArrayList<>();
}
