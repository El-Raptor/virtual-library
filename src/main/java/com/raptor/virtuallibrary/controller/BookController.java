package com.raptor.virtuallibrary.controller;

import com.raptor.virtuallibrary.entity.Book;
import com.raptor.virtuallibrary.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> findAllBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Book> findBookByIsbn(@RequestParam String isbn) {
        return new ResponseEntity<>(bookService.findByIsbn(isbn), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Book> findBookByTitle(@RequestParam String title) {
        return new ResponseEntity<>(bookService.findByTitle(title), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable int id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
