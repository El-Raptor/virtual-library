package com.raptor.virtuallibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AuthorCreateDTO {
    @NotBlank(message = "The author's name is mandatory.")
    private String name;

    private String nationality;
    private LocalDate birthDate;

    @Size(max = 1000, message = "The biography must have less than 1000 characters.")
    private String biography;
}
