package com.raptor.virtuallibrary.dto;

import lombok.Data;

@Data
public class BookDTO {
    private long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private Integer numberPages;
    private String publisher;

    private AuthorSummaryDTO author;
}
