package com.example.ms_user_interaction.repository;

import com.example.ms_user_interaction.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
