package com.example.ms_user_interaction.service.impl;


import com.example.ms_user_interaction.entity.Review;
import com.example.ms_user_interaction.repository.ReviewRepository;
import com.example.ms_user_interaction.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewServiceImpl implements ReviewService {


    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public List<Review> list() {
        return reviewRepository.findAll();
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> findById(Integer id) {
        return reviewRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        reviewRepository.deleteById(id);
    }

}
