package com.example.task.controllers;

import com.example.task.dtos.reviews.AddReviewRequestDTO;
import com.example.task.exceptions.BookNotFoundException;
import com.example.task.models.Review;
import com.example.task.services.BookService;
import com.example.task.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

  private final ReviewService reviewService;
  private final BookService   bookService;

  @Autowired
  public ReviewController(ReviewService reviewService, BookService bookService) {
    this.reviewService = reviewService;
    this.bookService = bookService;
  }

  @PostMapping
  public ResponseEntity<String> addReview(@RequestBody AddReviewRequestDTO requestDto) {
    try {
      // Check if the book with the specified ID exists
      bookService.get(requestDto.getBookId());
    } catch (BookNotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book doesn't exist.");
    }
    // Check if the reviewer with the specified email exists
    Review existingReview = reviewService.getReviewByReviewerEmailAndBookId(requestDto.getReviewerEmail(),
        requestDto.getBookId());
    if (existingReview != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reviewer has already reviewed this book.");
    }

    var review = new Review();
    review.setBookID(requestDto.getBookId());
    review.setReviewerEmail(requestDto.getReviewerEmail());
    review.setComment(requestDto.getComment());
    review.setRating(requestDto.getRating());
    reviewService.addReview(review);

    return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully.");
  }

}
