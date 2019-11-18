package com.hdt.example_assess.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;



@Entity
@Table(name = "book_bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book_Bill {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column
    private int counts;
}
