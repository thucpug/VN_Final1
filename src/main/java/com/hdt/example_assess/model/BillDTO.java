package com.hdt.example_assess.model;

import com.hdt.example_assess.entity.Book_Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private int id;
    private String namePerson;
    private String contentsBill;
    private Date createDate;
    private Date createDateEnded;
    private boolean status = true;
    private List<Book_BillDTO> book_billDTOS = new ArrayList<>();
}
