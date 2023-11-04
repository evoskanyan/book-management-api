package com.example.task.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private String reviewerEmail;

  @ManyToOne
  @JoinColumn(name = "book_id")
  @Getter
  @Setter
  private Book book;

  @NonNull
  @Getter
  @Setter
  private Integer rating;

  @Getter
  @Setter
  private String comment;
}

