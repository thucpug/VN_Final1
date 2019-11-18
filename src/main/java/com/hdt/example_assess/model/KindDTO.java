package com.hdt.example_assess.model;

import com.hdt.example_assess.entity.Book;
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
public class KindDTO {
    private int id;
    private String kindName;
    private Date createDate;
}
