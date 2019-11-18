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
@Table(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameBook;
    private String contentsBook;
    private String image;
    @CreationTimestamp
    private Date createDate;
    private long papers;
    private boolean status;
    private long amounts;
    @ManyToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne()
    @JoinColumn(name = "kind_id", referencedColumnName = "id")
    private Kind kind;

    @ManyToOne()
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Languages language;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    List<Book_Bill> bills = new ArrayList<>();
}
