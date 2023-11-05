package com.example.task.dtos.reviews;

import lombok.Data;

@Data
public class AddReviewRequestDTO {

  public String reviewerEmail;
  public Long   bookId;
  public String comment;
  public Integer rating;

}
