package com.raptor.virtuallibrary.repository;

import com.raptor.virtuallibrary.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
