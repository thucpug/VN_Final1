package com.hdt.example_assess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillReportDTO {
    private int id;
    private String namePerson;
    private String createDate;
    private String createDateEnded;
    private int counts;
    private String nameBook;
}
