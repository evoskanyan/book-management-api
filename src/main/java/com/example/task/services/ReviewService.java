package com.example.task.services;

import com.example.task.entities.Review;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

  Review addReview(Review review);

}