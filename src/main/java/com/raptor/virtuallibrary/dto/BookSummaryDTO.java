package com.raptor.virtuallibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class BookSummaryDTO {
    private long id;
    private String title;
    private String isbn;
}
