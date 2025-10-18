package com.raptor.virtuallibrary.service;

import com.raptor.virtuallibrary.entity.Book;
import com.raptor.virtuallibrary.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null);
    }

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }
}
