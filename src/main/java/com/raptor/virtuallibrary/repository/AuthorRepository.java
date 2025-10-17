package com.raptor.virtuallibrary.repository;

import com.raptor.virtuallibrary.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
