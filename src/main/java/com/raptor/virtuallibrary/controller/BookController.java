package com.raptor.virtuallibrary.controller;

import com.raptor.virtuallibrary.dto.BookCreateDTO;
import com.raptor.virtuallibrary.dto.BookDTO;
import com.raptor.virtuallibrary.entity.Book;
import com.raptor.virtuallibrary.mapper.BookMapper;
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

import java.net.URI;
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
    public ResponseEntity<List<BookDTO>> findAllBooks() {
        var books = bookService.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .toList();
        return new ResponseEntity<>(books, HttpStatus.OK);
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
    public ResponseEntity<BookDTO> findBookById(@Parameter(description = "Book's ID to be found")
                                                @PathVariable int id) {
        var book = bookService.findById(id);
        if (book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(BookMapper.toDTO(book), HttpStatus.OK);
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
    public ResponseEntity<BookDTO> findBookByIsbn(@Parameter(description = "Book's ISBN to be found")
                                               @RequestParam String isbn) {
        var book = bookService.findByIsbn(isbn);
        if (book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(BookMapper.toDTO(book), HttpStatus.OK);
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
    public ResponseEntity<BookDTO> findBookByTitle(@Parameter(description = "Book's title to be found")
                                                @RequestParam String title) {
        var book = bookService.findByTitle(title);
        if (book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(BookMapper.toDTO(book), HttpStatus.OK);
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
    public ResponseEntity<Book> addBook(@RequestBody BookCreateDTO bookDTO) {
        var book = BookMapper.toEntity(bookDTO);
        var newBook = bookService.save(book);
        var location = URI.create("/api/books/" + newBook.getId());
        return ResponseEntity.created(location).body(newBook);
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
    public ResponseEntity<BookDTO> updateBook(@Parameter(description = "Book's ID to be found")
                                           @PathVariable int id,
                                           @RequestBody Book book) {
        var existingBook = bookService.findById(id);

        if (existingBook == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        var updatedBook = bookService.save(book);
        return new ResponseEntity<>(BookMapper.toDTO(updatedBook), HttpStatus.OK);
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
    public ResponseEntity<Void> deleteBook(@Parameter(description = "Book's ID to be found")
                                           @PathVariable int id) {
        var book = bookService.findById(id);
        if (book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
