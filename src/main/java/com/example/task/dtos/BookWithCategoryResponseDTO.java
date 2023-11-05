package com.example.task.dtos;

import com.example.task.entities.Category;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

public class BookWithCategoryResponseDTO {

  @Getter
  @Setter
  private Long     id;
  @Getter
  @Setter
  private String   title;
  @Getter
  @Setter
  private String   author;
  @Getter
  @Setter
  @Lob
  private String   content;
  @Getter
  @Setter
  private Category category;
  @Getter
  @Setter
  private Double   avgRating;

}
