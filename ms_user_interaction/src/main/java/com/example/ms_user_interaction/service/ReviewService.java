package com.example.ms_user_interaction.service;

import com.example.ms_user_interaction.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    public List<Review> list();
    public Review save(Review review);
    public Review update(Review review);
    public Optional<Review> findById(Integer id);
    public void deleteById(Integer id);
}
