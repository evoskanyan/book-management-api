package com.example.task.dtos.books;

import jakarta.persistence.Lob;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BookDTO {

  private Long   id;
  private String title;
  private String author;
  @Lob
  private String content;
  private Long   categoryId;

}
