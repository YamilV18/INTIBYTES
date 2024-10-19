package com.example.ms_user_interaction.controller;


import com.example.ms_user_interaction.dto.ErrorResponseDto;
import com.example.ms_user_interaction.dto.UserDto;
import com.example.ms_user_interaction.entity.Review;
import com.example.ms_user_interaction.feign.UserFeign;
import com.example.ms_user_interaction.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> list() {
        List<Review> reviews = reviewService.list();
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<Review> save(@RequestBody Review review) {
        Review savedCategory = reviewService.save(review);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping
    public ResponseEntity<Review> update(@RequestBody Review review) {
        Review updatedReview = reviewService.update(review);
        return ResponseEntity.ok(updatedReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> listById(@PathVariable Integer id) {
        Optional<Review> review = reviewService.findById(id);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        reviewService.deleteById(id);
        return ResponseEntity.ok("Eliminación Correcta");
    }
}
