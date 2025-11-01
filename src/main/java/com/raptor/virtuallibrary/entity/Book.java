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

    public static class Builder {
        private long id;
        private String title;
        private String isbn;
        private int publicationYear;
        private int numberPages;
        private String publisher;
        private Author author;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder publicationYear(int publicationYear) {
            this.publicationYear = publicationYear;
            return this;
        }

        public Builder numberPages(int numberPages) {
            this.numberPages = numberPages;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder author(Author author) {
            this.author = author;
            return this;
        }

        public Book build() {
            return new Book(id, title, isbn, publicationYear, numberPages, publisher, author);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
