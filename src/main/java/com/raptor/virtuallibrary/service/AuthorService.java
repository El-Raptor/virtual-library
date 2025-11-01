package com.raptor.virtuallibrary.service;

import com.raptor.virtuallibrary.entity.Author;
import com.raptor.virtuallibrary.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author findByName(String name) {
        return authorRepository.findByName(name).orElse(null);
    }

    public List<Author> findAll() {
        return (List<Author>) authorRepository.findAll();
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }
}
