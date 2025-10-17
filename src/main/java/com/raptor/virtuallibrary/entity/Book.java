package com.raptor.virtuallibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book", schema = "virtual_library")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "number_pages")
    private int numberPages;
    private String publisher;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
