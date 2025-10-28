package com.raptor.virtuallibrary.mapper;

import com.raptor.virtuallibrary.dto.AuthorSummaryDTO;
import com.raptor.virtuallibrary.dto.BookCreateDTO;
import com.raptor.virtuallibrary.dto.BookDTO;
import com.raptor.virtuallibrary.entity.Author;
import com.raptor.virtuallibrary.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public static BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublicationYear(book.getPublicationYear());
        bookDTO.setNumberPages(book.getNumberPages());
        bookDTO.setPublisher(book.getPublisher());

        AuthorSummaryDTO authorDTO = new AuthorSummaryDTO();
        authorDTO.setId(book.getAuthor().getId());
        authorDTO.setName(book.getAuthor().getName());
        bookDTO.setAuthor(authorDTO);

        return bookDTO;
    }

    public static Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null)
            return null;

        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .publicationYear(bookDTO.getPublicationYear())
                .numberPages(bookDTO.getNumberPages())
                .publisher(bookDTO.getPublisher())
                .author(AuthorMapper.toEntity(bookDTO.getAuthor()))
                .build();
    }

    public static Book toEntity(BookCreateDTO bookCreateDTO) {
        if (bookCreateDTO == null)
            return null;

        return Book.builder()
                .title(bookCreateDTO.getTitle())
                .isbn(bookCreateDTO.getIsbn())
                .publicationYear(bookCreateDTO.getPublisherYear())
                .numberPages(bookCreateDTO.getNumberPages())
                .publisher(bookCreateDTO.getPublisher())
                .author(Author.builder()
                        .id(bookCreateDTO.getAuthorId())
                        .build())
                .build();
    }
}
