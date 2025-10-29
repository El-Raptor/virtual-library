package com.raptor.virtuallibrary.service;

import com.raptor.virtuallibrary.entity.Author;
import com.raptor.virtuallibrary.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Disabled("AuthorService Tests")
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        this.authorService = new AuthorService(authorRepository);
    }

    @Test
    @DisplayName("Should find all authors")
    void shouldFindAllAuthors() {
        List<Author> mockList = List.of(
                Author.builder()
                        .id(1)
                        .name("Drummond")
                        .nationality("Brasileiro")
                        .birthDate(LocalDate.of(1902, 10, 31))
                        .biography("Poeta")
                        .build(),
                Author.builder()
                        .id(2)
                        .name("Machado")
                        .nationality("Brasileiro")
                        .birthDate(LocalDate.of(1839, 6, 21))
                        .biography("Romancista")
                        .build()
        );

        Mockito.when(authorRepository.findAll()).thenReturn(mockList);

        var result = authorService.findAll();

        Assertions.assertEquals(mockList, result);
        Assertions.assertEquals(mockList.size(), result.size());
        Assertions.assertEquals(mockList.getFirst().getName(), result.getFirst().getName());
    }

    @Test
    @DisplayName("Should find author by ID")
    void shouldFindAuthorById() {
        List<Author> mockList = List.of(
                Author.builder()
                        .id(1)
                        .name("Drummond")
                        .build()
        );

        Mockito.when(authorRepository.findById(1)).thenReturn(Optional.of(mockList.getFirst()));

        var result = authorService.findById(1);

        Assertions.assertEquals(mockList.getFirst().getName(), result.getName());
    }

    @Test
    @DisplayName("Should find author by name")
    void shouldFindAuthorByName() {
        var mockAuthor = Author.builder()
                .id(1)
                .name("Drummond")
                .build();

        Mockito.when(authorRepository.findByName("Drummond"))
                .thenReturn(Optional.of(mockAuthor));

        var result = authorService.findByName("Drummond");

        Assertions.assertEquals(mockAuthor.getName(), result.getName());
        Assertions.assertEquals(mockAuthor.getId(), result.getId());

        Mockito.verify(authorRepository).findByName("Drummond");
        Mockito.verifyNoMoreInteractions(authorRepository);
    }

    @Test
    @DisplayName("Should save an author successfully")
    void shouldSaveAuthor() {
        Author author = Author.builder()
                .id(1)
                .name("Drummond")
                .build();

        Mockito.when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.save(author);

        Assertions.assertEquals(author, result);
        Mockito.verify(authorRepository).save(author);
    }

    @Test
    @DisplayName("Should throw exception when author not found by ID")
    void shouldThrowWhenAuthorNotFoundById() {
        Mockito.when(authorRepository.findById(99)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> authorService.findById(99));

        Mockito.verify(authorRepository).findById(99);
    }

    @Test
    @DisplayName("Should throw exception when author not found by name")
    void shouldThrowWhenAuthorNotFoundByName() {
        Mockito.when(authorRepository.findByName("Camões")).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> authorService.findByName("Camões"));

        Mockito.verify(authorRepository).findByName("Camões");
    }

    @Test
    @DisplayName("Should delete author by ID successfully")
    void shouldDeleteAuthorById() {
        Mockito.when(authorRepository.existsById(1)).thenReturn(true);

        authorService.deleteById(1);

        Mockito.verify(authorRepository).existsById(1);
        Mockito.verify(authorRepository).deleteById(1);
    }

    @Test
    @DisplayName("Should throw exception when trying to delete non-existing author")
    void shouldThrowWhenDeletingNonExistingAuthor() {
        Mockito.when(authorRepository.existsById(99)).thenReturn(false);

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> authorService.deleteById(99));

        Mockito.verify(authorRepository).existsById(99);
        Mockito.verify(authorRepository, Mockito.never()).deleteById(Mockito.any());
    }
}
