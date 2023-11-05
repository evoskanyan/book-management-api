package com.example.task.services;

import com.example.task.models.Book;
import com.example.task.exceptions.BookNotFoundException;
import com.example.task.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

  private static final Long   ID            = 1L;
  private static final String SEARCH_STRING = "sample";
  private static final String ERROR_MESSAGE = "Book is not found";

  @InjectMocks
  private BookServiceImpl bookService;
  @Mock
  private BookRepository  repository;
  @Mock
  private Book            book;

  @Test
  public void shouldGetBook() {
    when(repository.findById(ID)).thenReturn(Optional.of(book));

    Book result = bookService.get(ID);

    assertThat(result).isNotNull().isEqualTo(book);

    verify(repository).findById(ID);
  }

  @Test
  public void shouldHandleBookNotFound() {
    when(repository.findById(ID)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> bookService.get(ID)).isInstanceOf(BookNotFoundException.class).hasMessage(ERROR_MESSAGE);

    verify(repository).findById(ID);
  }

  @Test
  public void shouldGetAllBooks() {
    when(repository.findAllBooks()).thenReturn(List.of(book));

    List<Book> result = bookService.getAll();

    assertThat(result).isNotNull().hasSize(1).containsExactly(book);

    verify(repository).findAllBooks();
  }

  @Test
  public void shouldSearchBooks() {
    when(repository.search(SEARCH_STRING)).thenReturn(List.of(book));

    List<Book> result = bookService.search(SEARCH_STRING);

    assertThat(result).isNotNull().hasSize(1).containsExactly(book);

    verify(repository).search(SEARCH_STRING);
  }

  @Test
  public void shouldSaveBook() {
    when(repository.save(any(Book.class))).thenReturn(book);

    Book result = bookService.save(book);

    assertThat(result).isNotNull().isEqualTo(book);

    verify(repository).save(book);
  }

  @Test
  public void shouldDeleteBook() {
    when(repository.findById(ID)).thenReturn(Optional.of(book));

    bookService.delete(ID);

    verify(repository).delete(book);
  }

}
