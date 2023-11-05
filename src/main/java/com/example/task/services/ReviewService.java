package com.example.task.services;

import com.example.task.entities.Review;
import com.example.task.exceptions.ReviewNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

  Review getById(Long id) throws ReviewNotFoundException;

  Review addReview(Review review);

  Review getReviewByReviewerEmailAndBookId(String reviewerEmail, Long bookId);

  Double getAverageRatingByBookId(Long bookId);

  void deleteReview(Long id) throws ReviewNotFoundException;

}
