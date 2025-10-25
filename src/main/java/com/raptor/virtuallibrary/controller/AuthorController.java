package com.raptor.virtuallibrary.controller;

import com.raptor.virtuallibrary.entity.Author;
import com.raptor.virtuallibrary.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Get all authors from database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Authors found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)
                    )
            )
    })
    @GetMapping("/")
    public ResponseEntity<List<Author>> findAll() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }

    @Operation(description = "Get author by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Author found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Author not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@Parameter(description = "Author's ID", required = true)
                                           @PathVariable int id) {
        var author = authorService.findById(id);
        if (author == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @Operation(description = "Get Author by its name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Author found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Author not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<Author> findByName(@Parameter(description = "Author Name") @RequestParam String name) {
        var author = authorService.findByName(name);
        if (author == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @Operation(description = "Create new Author")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "New Author Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)
                    )
            )
    })
    @PostMapping("/")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        var newAuthor = authorService.save(author);
        URI location = URI.create("/api/author/" + newAuthor.getId());
        return ResponseEntity.created(location).body(newAuthor);
    }

    @Operation(description = "Update Author by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Author updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Author to be updated not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author,
                                               @Parameter(description = "Author's ID to be updated")
                                               @PathVariable Integer id) {
        var existingAuthor = authorService.findById(id);
        if (existingAuthor == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        author.setId(id);
        var updatedAuthor = authorService.save(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @Operation(description = "Delete Author by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Author ID has been removed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }
}
