package com.example.task.exceptions;

public class CategoryNotFoundException extends NullPointerException{
  public CategoryNotFoundException() {
    super("Category is not found");
  }

}
