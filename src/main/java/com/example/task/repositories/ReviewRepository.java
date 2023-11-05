package com.example.task.repositories;

import com.example.task.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  @Query(nativeQuery = true, value = "SELECT * FROM reviews  WHERE reviewer_email = :reviewerEmail AND book_id = " +
      ":bookId")
  Review findByReviewerEmailAndBookID(String reviewerEmail, Long bookId);

  @Query(nativeQuery = true, value = "SELECT * FROM reviews  WHERE reviewer_email = :reviewerEmail")
  Review findByReviewerEmail(String reviewerEmail);

  @Query(nativeQuery = true, value = "SELECT avg(rating) from reviews where book_id=:bookID group by book_id")
  Double findAverageRating(Long bookID);

}
