package com.example.task.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Entity
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "BookID")
  @Setter
  private Long id;

  @Column(name = "Title", nullable = false)
  @Getter
  @Setter
  private String title;

  @Column(name = "Author", nullable = false)
  @Getter
  @Setter
  private String author;

  @Getter
  @Setter
  @Column(name = "Content", columnDefinition = "TEXT")
  @Lob
  private String content;

  @Getter
  @Setter
  @Column(name = "CategoryID")
  private Long categoryId;

}
