package com.raptor.virtuallibrary.repository;

import com.raptor.virtuallibrary.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Optional<Author> findByName(String name);
}
