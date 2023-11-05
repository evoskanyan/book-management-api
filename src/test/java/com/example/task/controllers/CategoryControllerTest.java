package com.example.task.controllers;

import com.example.task.dtos.CategoryDTO;
import com.example.task.models.Category;
import com.example.task.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

  private static final Long   CATEGORY_ID   = 1L;
  private static final String CATEGORY_NAME = "Fiction";
  private static final String ERROR_MESSAGE = "Failed to add category: Some error message";

  @InjectMocks
  private CategoryController categoryController;

  @Mock
  private CategoryService categoryService;
  @Mock
  private Category        category;

  @Test
  public void shouldGetCategoryById() {
    when(category.getId()).thenReturn(CATEGORY_ID);
    when(category.getName()).thenReturn(CATEGORY_NAME);
    when(categoryService.get(1L)).thenReturn(category);

    CategoryDTO result = categoryController.getCategoryById(1L);

    assertThat(result)
        .isNotNull()
        .returns(CATEGORY_ID, CategoryDTO::getId)
        .returns(CATEGORY_NAME, CategoryDTO::getName);

    verify(categoryService).get(CATEGORY_ID);
  }

  @Test
  public void shouldGetCategoryByName() {
    when(category.getId()).thenReturn(CATEGORY_ID);
    when(category.getName()).thenReturn(CATEGORY_NAME);
    when(categoryService.get(CATEGORY_NAME)).thenReturn(category);

    CategoryDTO result = categoryController.getCategoryByName(CATEGORY_NAME);

    assertThat(result)
        .isNotNull()
        .returns(CATEGORY_ID, CategoryDTO::getId)
        .returns(CATEGORY_NAME, CategoryDTO::getName);

    verify(categoryService).get(CATEGORY_NAME);
  }

//  @Test
//  public void shouldAddCategory() {
//    when(categoryService.save(any(Category.class))).thenReturn(category);
//
//    ResponseEntity<String> result = categoryController.addCategory(CATEGORY_ID,CATEGORY_NAME);
//
//    assertThat(result)
//        .isNotNull()
//        .returns(HttpStatus.OK, ResponseEntity::getStatusCode)
//        .returns("Category added successfully.", ResponseEntity::getBody);
//  }

//  @Test
//  public void shouldHandleCategoryAddError() {
//    when(categoryService.save(any(Category.class))).thenThrow(new RuntimeException("Some error message"));
//
//    ResponseEntity<String> result = categoryController.addCategory(CATEGORY_ID,CATEGORY_NAME);
//
//    assertThat(result)
//        .isNotNull()
//        .returns(HttpStatus.BAD_REQUEST, ResponseEntity::getStatusCode)
//        .returns(ERROR_MESSAGE, ResponseEntity::getBody);
//
//  }

}
