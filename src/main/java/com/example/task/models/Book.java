package com.example.task.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")
  @Setter
  private Long id;

  @Column(name = "title", nullable = false)
  @Getter
  @Setter
  private String title;

  @Column(name = "author", nullable = false)
  @Getter
  @Setter
  private String author;

  @Getter
  @Setter
  @Column(name = "content", columnDefinition = "TEXT")
  @Lob
  private String content;

  @Getter
  @Setter
  @Column(name = "category_id")
  private Long categoryId;

}
