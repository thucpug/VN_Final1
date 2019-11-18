package com.hdt.example_assess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book_BillDTO {
    private int id;
    private int bill_id;
    private String billCustomerName;
    private int book_id;
    private String bookName;
    private int counts;
}
