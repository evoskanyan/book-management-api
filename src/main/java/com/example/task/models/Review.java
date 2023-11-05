package com.example.task.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Entity
@Table(name = "reviews")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

