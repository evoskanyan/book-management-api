package com.example.task.controllers;

import com.example.task.dtos.BookDTO;
import com.example.task.dtos.BookWithCategoryResponseDTO;
import com.example.task.entities.Book;
import com.example.task.entities.Category;
import com.example.task.exceptions.CategoryNotFoundException;
import com.example.task.services.BookService;
import com.example.task.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService     bookService;
  private final CategoryService categoryService;

  @GetMapping("/{id}")
  public BookDTO getBookById(@PathVariable Long id) {
    BookDTO bookDTO = new BookDTO();
    Book book = bookService.get(id);
    bookDTO.setAuthor(book.getAuthor());
    bookDTO.setTitle(book.getTitle());
    bookDTO.setId(book.getId());
    bookDTO.setContent(book.getContent());
    bookDTO.setCategoryId(book.getCategoryId());
    return bookDTO;
  }

  @GetMapping("/all")
  public List<BookWithCategoryResponseDTO> getAllBooks() {
    BookWithCategoryResponseDTO bookDTO = new BookWithCategoryResponseDTO();
    List<Book> books = bookService.getAll();
    List<BookWithCategoryResponseDTO> dtos = new ArrayList<>();
    for (Book book : books) {
      bookDTO.setAuthor(book.getAuthor());
      bookDTO.setTitle(book.getTitle());
      bookDTO.setId(book.getId());
      bookDTO.setContent(book.getContent());
      Category category = categoryService.get(book.getCategoryId());
      bookDTO.setCategory(category);
      dtos.add(bookDTO);
    }
    return dtos;
  }

  @PostMapping("/add")
  public ResponseEntity<String> addBook(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
      @RequestParam("author") String author, @RequestParam("categoryName") String categoryName) throws IOException {

    if (file.getSize() > 5 * 1024 * 1024) {
      return ResponseEntity.badRequest().body("File size exceeds the limit.");
    }
    Category category;
    try {

      category = categoryService.get(categoryName);

    } catch (CategoryNotFoundException ex) {
      category = new Category();
      category.setName(categoryName);
      categoryService.save(category);
    }

    Book book = new Book();
    book.setTitle(title);
    book.setAuthor(author);
    book.setCategoryId(category.getId());

    String content;
    try (PDDocument document = PDDocument.load(file.getBytes())) {
      PDFTextStripper textStripper = new PDFTextStripper();
      content = textStripper.getText(document);
    }
    book.setContent(content);

    bookService.save(book);

    return ResponseEntity.ok("Book added successfully.");
  }

  @GetMapping("/search")
  public ResponseEntity<List<Book>> searchBooks(@RequestParam(value = "query") String query) {
    try {
      List<Book> searchResults = bookService.search(query.toLowerCase());

      if (searchResults.isEmpty()) {
        return ResponseEntity.notFound().build();
      } else {
        return ResponseEntity.ok(searchResults);
      }
    } catch (Exception e) {
      return null;
    }
  }

}