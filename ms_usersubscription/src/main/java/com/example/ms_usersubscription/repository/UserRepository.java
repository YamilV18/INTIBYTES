package com.example.ms_usersubscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.example.ms_usersubscription.entity.User, Integer> {
}
