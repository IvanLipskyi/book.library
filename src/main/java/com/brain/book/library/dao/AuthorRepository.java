package com.brain.book.library.dao;

import com.brain.book.library.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    //волшебный метод
    Optional<Author> getAuthorByNameAndSecondNameAndLastName(String name, String secondName, String lastName);

    //именованный запрос
    @Query("from Author a where a.name = ?1 and a.secondName = ?2 and a.lastName = ?3")
    Optional<Author>getAuthorByFullName(String name, String secondName, String lastName);

}
