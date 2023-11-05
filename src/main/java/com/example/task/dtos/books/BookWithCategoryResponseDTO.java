package com.example.task.dtos.books;

import com.example.task.models.Category;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BookWithCategoryResponseDTO {

  private Long     id;
  private String   title;
  private String   author;
  @Lob
  private String   content;
  private Category category;
  private Double   avgRating;

}
