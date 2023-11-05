package com.example.task.controllers;

import com.example.task.dtos.CategoryDTO;
import com.example.task.entities.Category;
import com.example.task.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/{id}")
  public CategoryDTO getCategoryById(@PathVariable Long id) {
    CategoryDTO categoryDTO = new CategoryDTO();
    Category category = categoryService.get(id);
    categoryDTO.setId(category.getId());
    categoryDTO.setName(category.getName());
    return categoryDTO;
  }
  @GetMapping("/by/{name}")
  public CategoryDTO getCategoryByName(@PathVariable String name) {
    CategoryDTO categoryDTO = new CategoryDTO();
    Category category = categoryService.get(name);
    categoryDTO.setId(category.getId());
    categoryDTO.setName(category.getName());
    return categoryDTO;
  }
  @PostMapping
  public ResponseEntity<String> addCategory(@RequestParam("categoryId") Long categoryId,
      @RequestParam("categoryName") String categoryName) {
    Category category=new Category();
    category.setId(categoryId);
    category.setName(categoryName);
    try {
      categoryService.save(category);
      return ResponseEntity.ok("Category added successfully.");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Failed to add category: " + e.getMessage());
    }
  }
}
