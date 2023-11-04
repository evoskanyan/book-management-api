package com.example.task.services;

import com.example.task.entities.Book;

import java.util.List;

public interface BookService {

  Book get(Long id);
  List<Book> getAll();
  List<Book> search(String searchString);
  Book save(Book article);
  void delete(Long id);

}
