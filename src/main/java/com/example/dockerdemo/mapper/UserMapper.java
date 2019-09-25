package com.example.dockerdemo.mapper;

import com.example.dockerdemo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    void addUser(User user);
    void deleteUser(int id);
    User getUser(int id);
}
