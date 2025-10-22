package com.raptor.virtuallibrary.controller;

import com.raptor.virtuallibrary.entity.Author;
import com.raptor.virtuallibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // TODO: Aplicar Swagger

    @GetMapping("/")
    public ResponseEntity<List<Author>> findAll() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable int id) {
        return new ResponseEntity<>(authorService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Author> findByName(@PathVariable String name) {
        return new ResponseEntity<>(authorService.findByName(name), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        var newAuthor = authorService.save(author);
        URI location =URI.create("/api/author/" + newAuthor.getId());
        return ResponseEntity.created(location).body(newAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable Integer id) {
        var newAuthor = authorService.save(author);
        return ResponseEntity.ok(newAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }
}
