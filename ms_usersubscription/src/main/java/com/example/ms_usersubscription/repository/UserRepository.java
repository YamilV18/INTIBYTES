package com.example.ms_usersubscription.repository;

import com.example.ms_usersubscription.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
