package com.raptor.virtuallibrary.mapper;

import com.raptor.virtuallibrary.dto.AuthorCreateDTO;
import com.raptor.virtuallibrary.dto.AuthorDTO;
import com.raptor.virtuallibrary.dto.AuthorSummaryDTO;
import com.raptor.virtuallibrary.entity.Author;

public class AuthorMapper {
    public static AuthorDTO toDTO(Author author) {
        if (author == null)
            return null;

        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .nationality(author.getNationality())
                .birthDate(author.getBirthDate())
                .biography(author.getBiography())
                .build();
    }

    public static Author toEntity(AuthorCreateDTO dto) {
        if (dto == null)
            return null;

        return Author.builder()
                .name(dto.getName())
                .nationality(dto.getNationality())
                .birthDate(dto.getBirthDate())
                .biography(dto.getBiography())
                .build();
    }

    public static Author toEntity(AuthorDTO dto) {
        if (dto == null)
            return null;

        return Author.builder()
                .id(dto.getId())
                .name(dto.getName())
                .nationality(dto.getNationality())
                .birthDate(dto.getBirthDate())
                .biography(dto.getBiography())
                .build();
    }

    public static Author toEntity(AuthorSummaryDTO dto) {

        if (dto == null)
            return null;

        return Author.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
