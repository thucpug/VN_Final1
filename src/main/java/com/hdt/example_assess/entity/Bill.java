package com.hdt.example_assess.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String namePerson;
    private String contentsBill;
    @CreationTimestamp
    private Date createDate;
    private Date createDateEnded;
    private boolean status = true;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Book_Bill> books = new ArrayList<>();


//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "book_bill",
//            joinColumns = {@JoinColumn(name = "bill_id")},
//            inverseJoinColumns = {@JoinColumn(name = "book_id")})
//    private List<Bill> bills = new ArrayList<>();

}
