package com.example.task.services;

import com.example.task.entities.Category;

public interface CategoryService {

  Category get(Long categoryId);
  Category get(String categoryName);

  Category save(Category category);

  void delete(Long id);
}