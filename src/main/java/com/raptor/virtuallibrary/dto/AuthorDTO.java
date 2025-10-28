package com.raptor.virtuallibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {
    private int id;
    private String name;
    private String nationality;
    private LocalDate birthDate;
    private String biography;
    private List<BookSummaryDTO> books;
}
