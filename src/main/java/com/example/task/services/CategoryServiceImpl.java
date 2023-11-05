package com.example.task.services;

import com.example.task.models.Category;
import com.example.task.exceptions.CategoryNotFoundException;
import com.example.task.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repository;

  public CategoryServiceImpl(CategoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public Category get(Long id) {
    Optional<Category> category = repository.findById(id);
    return category.orElseThrow(CategoryNotFoundException::new);
  }

  @Override
  public Category get(final String categoryName) {
    Optional<Category> category = repository.findByName(categoryName);
    return category.orElseThrow(CategoryNotFoundException::new);
  }

  @Override
  public Category save(Category category) {
    repository.save(category);
    return category;
  }


  @Override
  public void delete(Long id) {
    Optional<Category> category = repository.findById(id);
    repository.delete(category.orElseThrow(CategoryNotFoundException::new));
  }

}
