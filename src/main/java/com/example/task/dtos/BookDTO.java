package com.example.task.dtos;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

public class BookDTO {

  @Getter
  @Setter
  private Long   id;
  @Getter
  @Setter
  private String title;
  @Getter
  @Setter
  private String author;
  @Getter
  @Setter
  @Lob
  private String content;
  @Getter
  @Setter
  private Long   categoryId;

}
