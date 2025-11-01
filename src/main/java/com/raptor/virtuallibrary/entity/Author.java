package com.raptor.virtuallibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "author", schema = "virtual_library")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public static class Builder {
        private int id;
        private String name;
        private String nationality;
        private LocalDate birthDate;
        private String biography;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder biography(String biography) {
            this.biography = biography;
            return this;
        }

        public Author build() {
            return new Author(id, name, nationality, birthDate, biography, null);
        }

    }
    public static Builder builder() {
        return new Builder();
    }

}
