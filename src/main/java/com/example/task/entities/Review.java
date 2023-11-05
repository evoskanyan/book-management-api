package com.example.task.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  @Column(name = "id")
  private Long id;

  @Getter
  @Setter
  @Column(name = "reviewer_email")
  private String reviewerEmail;

  @Getter
  @Setter
  @Column(name = "book_id")
  private Long bookID;

  @NonNull
  @Getter
  @Setter
  @Column(name = "rating")
  private Integer rating;

  @Getter
  @Setter
  @Column(name = "comment")
  private String comment;
}

