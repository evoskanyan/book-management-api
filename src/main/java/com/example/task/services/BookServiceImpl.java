package com.example.task.services;

import com.example.task.models.Book;
import com.example.task.exceptions.BookNotFoundException;
import com.example.task.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  public BookServiceImpl(BookRepository repository) {
    this.repository = repository;
  }

  @Override
  public Book get(Long id) {
    Optional<Book> book = repository.findById(id);
    return book.orElseThrow(BookNotFoundException::new);
  }

  @Override
  public List<Book> getAll() {
    return repository.findAllBooks();
  }

  @Override
  public List<Book> search(final String searchString) {
    return repository.search(searchString.toLowerCase());
  }

  @Override
  public Book save(Book reader) {
    repository.save(reader);
    return reader;
  }
  @Override
  public void delete(Long id) {
    Optional<Book> author = repository.findById(id);
    repository.delete(author.orElseThrow(BookNotFoundException::new));
  }

}
