package com.example.task.exceptions;

public class ReviewNotFoundException extends NotFoundException {

  public ReviewNotFoundException() {
    super("Review is not found");
  }

}
