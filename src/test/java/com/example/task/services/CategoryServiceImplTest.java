package com.example.task.services;

import com.example.task.entities.Category;
import com.example.task.exceptions.CategoryNotFoundException;
import com.example.task.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

  private static final Long   ID            = 1L;
  private static final String CATEGORY_NAME = "Sample";
  private static final String ERROR_MESSAGE = "Category is not found";

  @InjectMocks
  private CategoryServiceImpl categoryService;

  @Mock
  private CategoryRepository repository;

  @Mock
  private Category category;

  @Test
  public void shouldGetCategoryById() {
    when(repository.findById(ID)).thenReturn(Optional.of(category));

    Category result = categoryService.get(ID);

    assertThat(result).isNotNull().isEqualTo(category);

    verify(repository).findById(ID);
  }

  @Test
  public void shouldHandleCategoryNotFoundById() {
    when(repository.findById(ID)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> categoryService.get(ID))
        .isInstanceOf(CategoryNotFoundException.class)
        .hasMessage(ERROR_MESSAGE);

    verify(repository).findById(ID);
  }

  @Test
  public void shouldGetCategoryByName() {
    when(repository.findByName(CATEGORY_NAME)).thenReturn(Optional.of(category));

    Category result = categoryService.get(CATEGORY_NAME);

    assertThat(result).isNotNull().isEqualTo(category);

    verify(repository).findByName(CATEGORY_NAME);
  }

  @Test
  public void shouldHandleCategoryNotFoundByName() {
    when(repository.findByName(CATEGORY_NAME)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> categoryService.get(CATEGORY_NAME))
        .isInstanceOf(CategoryNotFoundException.class)
        .hasMessage(ERROR_MESSAGE);

    verify(repository).findByName(CATEGORY_NAME);
  }

  @Test
  public void shouldSaveCategory() {
    when(repository.save(any(Category.class))).thenReturn(category);

    Category result = categoryService.save(category);

    assertThat(result).isNotNull().isEqualTo(category);

    verify(repository).save(category);
  }

  @Test
  public void shouldDeleteCategory() {
    when(repository.findById(ID)).thenReturn(Optional.of(category));

    categoryService.delete(ID);

    verify(repository).delete(category);
  }

}
