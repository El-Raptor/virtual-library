package com.raptor.virtuallibrary.controller;

import com.raptor.virtuallibrary.entity.Book;
import com.raptor.virtuallibrary.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Get all the books")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Books Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            )
    })
    @GetMapping("/")
    public ResponseEntity<List<Book>> findAllBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @Operation(description = "Get a book by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "No Book Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Book Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@Parameter(description = "Book's ID to be found")
                                             @PathVariable int id) {
        var book = bookService.findById(id);
        if (book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Operation(description = "Get a book by its ISBN")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "No Book Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Book Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            )
    })
    @GetMapping("/byIsbn")
    public ResponseEntity<Book> findBookByIsbn(@Parameter(description = "Book's ISBN to be found")
                                               @RequestParam String isbn) {
        var book = bookService.findByIsbn(isbn);
        if (book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Operation(description = "Get a book by its title name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "No Book Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Book Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            )
    })
    @GetMapping("/byTitle")
    public ResponseEntity<Book> findBookByTitle(@Parameter(description = "Book's title to be found")
                                                @RequestParam String title) {
        return new ResponseEntity<>(bookService.findByTitle(title), HttpStatus.OK);
    }

    @Operation(description = "Create a new Book")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Book Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            )
    })
    @PostMapping("/")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @Operation(description = "Update a book by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "No Book Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Book Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Parameter(description = "Book's ID to be found")
                                           @PathVariable int id,
                                           @RequestBody Book book) {
        var existingBook = bookService.findById(id);

        if (existingBook == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        var updatedBook = bookService.save(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @Operation(description = "Remove a book by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "No Book Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Book Deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@Parameter(description = "Book's ID to be found")
                                               @PathVariable int id) {
        var book =   bookService.findById(id);
        if (book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
