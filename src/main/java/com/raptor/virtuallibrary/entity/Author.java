package com.raptor.virtuallibrary.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "author", schema = "virtual_library")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id") // SQL ID naming convention
    private int id;

    @Column(nullable = false)
    private String name;

    private String nationality;

    private LocalDate birthDate;

    @Column(length = 1000)
    private String biography;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

}
