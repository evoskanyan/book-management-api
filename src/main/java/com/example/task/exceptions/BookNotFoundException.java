package com.example.task.exceptions;



public class BookNotFoundException extends NotFoundException {

  public BookNotFoundException() {
    super("Book is not found");
  }

}
