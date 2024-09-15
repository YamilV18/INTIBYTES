package com.example.msservices.controller;

import com.example.msservices.entity.Category;
import com.example.msservices.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Category> create(Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
        category.setId(id);
        return ResponseEntity.ok(categoryService.save(category));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Category>> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok(categoryService.findAll());
    }
}
