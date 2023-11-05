package com.example.task.controllers;

import com.example.task.dtos.books.BookDTO;
import com.example.task.dtos.books.BookWithCategoryResponseDTO;
import com.example.task.models.Book;
import com.example.task.models.Category;
import com.example.task.services.BookService;
import com.example.task.services.CategoryService;
import com.example.task.services.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

  private static final Long   ID            = 1L;
  private static final Long   ID_2          = 2L;
  private static final Long   CATEGORY_ID   = 2L;
  private static final Long   FILE_SIZE     = 1024L;
  private static final Double RATING        = 5.0;
  private static final String TITLE         = "Title";
  private static final String AUTHOR        = "Author";
  private static final String CONTENT       = "Content";
  private static final String CATEGORY_NAME = "Fiction";
  private static final String ERROR_MESSAGE = "Error while processing the PDF file.";
  private static final byte[] pdfContent    = "Sample PDF content".getBytes();

  @InjectMocks
  private BookController bookController;

  @Mock
  private BookService bookService;

  @Mock
  private ReviewService   reviewService;
  @Mock
  private Book            book;
  @Mock
  private Book            book1;
  @Mock
  private CategoryService categoryService;
  @Mock
  private MultipartFile   file;
  @Mock
  private Category        category;

  @Test
  public void shouldGetBookById() {
    when(book.getId()).thenReturn(ID);
    when(book.getCategoryId()).thenReturn(CATEGORY_ID);
    when(book.getAuthor()).thenReturn(AUTHOR);
    when(book.getContent()).thenReturn(CONTENT);
    when(book.getTitle()).thenReturn(TITLE);
    when(bookService.get(1L)).thenReturn(book);

    BookDTO result = bookController.getBookById(1L);

    assertThat(result)
        .isNotNull()
        .returns(ID, BookDTO::getId)
        .returns(CATEGORY_ID, BookDTO::getCategoryId)
        .returns(AUTHOR, BookDTO::getAuthor)
        .returns(CONTENT, BookDTO::getContent)
        .returns(TITLE, BookDTO::getTitle);

    verify(bookService).get(ID);
  }

  @Test
  public void shouldGetAllBooks() {
    when(book.getId()).thenReturn(ID);
    when(book.getCategoryId()).thenReturn(CATEGORY_ID);
    when(book.getAuthor()).thenReturn(AUTHOR);
    when(book.getContent()).thenReturn(CONTENT);
    when(book.getTitle()).thenReturn(TITLE);
    when(book1.getId()).thenReturn(ID_2);
    when(book1.getCategoryId()).thenReturn(CATEGORY_ID);
    when(book1.getAuthor()).thenReturn(AUTHOR);
    when(book1.getContent()).thenReturn(CONTENT);
    when(book1.getTitle()).thenReturn(TITLE);
    when(reviewService.getAverageRatingByBookId(any())).thenReturn(RATING);

    List<Book> books = List.of(book, book1);

    when(bookService.getAll()).thenReturn(books);

    List<BookWithCategoryResponseDTO> result = bookController.getAllBooks();

    assertThat(result).isNotNull().hasSize(2);
    assertThat(result.get(0))
        .returns(ID, BookWithCategoryResponseDTO::getId)
        .returns(AUTHOR, BookWithCategoryResponseDTO::getAuthor)
        .returns(CONTENT, BookWithCategoryResponseDTO::getContent)
        .returns(TITLE, BookWithCategoryResponseDTO::getTitle);

    verify(bookService).getAll();
  }

  @Test
  public void shouldAddBookWithInvalidPDF() throws IOException {
    when(file.getSize()).thenReturn(FILE_SIZE);
    when(file.getBytes()).thenReturn(pdfContent);
    when(category.getId()).thenReturn(ID);
    when(category.getName()).thenReturn(CATEGORY_NAME);
    when(categoryService.get(CATEGORY_NAME)).thenReturn(category);

    ResponseEntity<String> result = bookController.addBook(file, TITLE, AUTHOR, category.getName());

    assertThat(result)
        .isNotNull()
        .returns(HttpStatus.INTERNAL_SERVER_ERROR, ResponseEntity::getStatusCode)
        .returns(ERROR_MESSAGE, ResponseEntity::getBody);

    verify(categoryService).get(CATEGORY_NAME);
  }

}
