package com.example.ms_usersubscription.service;



import com.example.ms_usersubscription.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> list();
    public User save(User user);
    public User update(User user);
    public Optional<User> findById(Integer id);
    public void deleteById(Integer id);
}
