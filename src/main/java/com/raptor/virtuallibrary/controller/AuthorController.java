package com.raptor.virtuallibrary.controller;

import com.raptor.virtuallibrary.entity.Author;
import com.raptor.virtuallibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        var newAuthor = authorService.save(author);
        URI location =URI.create("/api/author/" + newAuthor.getId());
        return ResponseEntity.created(location).body(newAuthor);
    }
}
