package com.example.msservices.service;

import com.example.msservices.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> findAll();
    public Optional<Category> findById(Integer id);
    public Category save(Category category);
    public Category update(Category category);
    public void delete(Integer id);

    // Método para verificar si existe la categoría
    public boolean existsById(Integer id);
}
