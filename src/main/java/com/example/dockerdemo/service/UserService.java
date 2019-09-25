package com.example.dockerdemo.service;

import com.example.dockerdemo.entity.User;
import com.example.dockerdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.addUser(user);
    }
    public void deleteUser(int id){
        userMapper.deleteUser(id);
    }
    public User getUser(int id){
        return userMapper.getUser(id);
    }
}
