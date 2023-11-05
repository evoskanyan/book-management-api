package com.example.task.repositories;

import com.example.task.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

  @Query(nativeQuery = true, value = "select book_id, title, author, content, c.category_id, name from books join " +
      "categories c on c.category_id = books.category_id")
  List<Book> findAllBooks();

  @Query(nativeQuery = true, value = "SELECT * FROM books where LOWER(title) like ?1% or LOWER(author) like ?1% or " +
      "LOWER(content) like ?1%")
  List<Book> search(String searchString);

}