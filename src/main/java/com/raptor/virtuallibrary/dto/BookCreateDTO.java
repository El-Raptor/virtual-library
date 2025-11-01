package com.raptor.virtuallibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class BookCreateDTO {
    @NotBlank(message = "Book's title is mandatory.")
    private String title;

    @NotBlank(message = "ISBN is mandatory.")
    private String isbn;

    private Integer publisherYear;
    private Integer numberPages;
    private String publisher;

    @NotNull(message = "Author's ID is mandatory.")
    private int authorId;
}
