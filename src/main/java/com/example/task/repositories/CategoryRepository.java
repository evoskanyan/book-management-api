package com.example.task.repositories;

import com.example.task.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
  @Query(nativeQuery = true,value = "SELECT * FROM categories WHERE Name = :categoryName")
  Optional<Category> findByName(@Param("categoryName") String categoryName);
}
