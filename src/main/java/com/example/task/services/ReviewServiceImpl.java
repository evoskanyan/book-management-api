package com.example.task.services;

import com.example.task.entities.Review;
import com.example.task.exceptions.ReviewNotFoundException;
import com.example.task.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  @Autowired
  public ReviewServiceImpl(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  @Override
  public Review getById(Long id) throws ReviewNotFoundException {
    Optional<Review> review = reviewRepository.findById(id);
    return review.orElseThrow(ReviewNotFoundException::new);
  }

  @Override
  public Review getReviewByReviewerEmailAndBookId(String reviewerEmail, Long bookId) {
    return reviewRepository.findByReviewerEmailAndBookID(reviewerEmail, bookId);
  }

  @Override
  public Double getAverageRatingByBookId(final Long bookId) {
    return reviewRepository.findAverageRating(bookId);
  }

  @Override
  public Review addReview(Review review) {
    return reviewRepository.save(review);
  }

  public Review getReviewByReviewerEmail(String reviewerEmail) {
    return reviewRepository.findByReviewerEmail(reviewerEmail);
  }

  @Override
  public void deleteReview(Long id) throws ReviewNotFoundException {
    Review review = getById(id);
    reviewRepository.delete(review);
  }

}
