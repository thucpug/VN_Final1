package com.hdt.example_assess.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "kind")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kind {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "kindName")
    private String kindName;
    @OneToMany(mappedBy = "kind", fetch = FetchType.LAZY)
    List<Book> Books = new ArrayList<>();
    @CreationTimestamp
    private Date createDate;
}