package com.example.task.repositories;

import com.example.task.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

  @Query(nativeQuery = true, value = "select BookID, Title, Author, Content, c.CategoryID, Name from book join " +
      "mydatabase.category c on c.CategoryID = book.CategoryID")
  List<Book> findAllBooks();

  @Query(nativeQuery = true, value = "SELECT * FROM book where LOWER(title) like ?1% or LOWER(author) like ?1% or " +
      "LOWER(content) like ?1%")
  List<Book> search(String searchString);

}