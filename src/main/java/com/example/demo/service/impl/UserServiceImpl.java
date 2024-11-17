package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.repos.UserRepo;
import com.example.demo.rest.dto.UserDtos.NewUserRequest;
import com.example.demo.rest.dto.UserDtos.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void save(User user) {
        userRepo.saveAndFlush(user);
    }
    @Override
    public User findById(Long id) {
        Optional<User> element = userRepo.findById(id);
        User usr = element.get();
        return usr;
    }
    @Override
    public Long addNewUser(NewUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepo.saveAndFlush(user);
        return user.getId();


    }
    @Override
    public void  deleteById(Long id) {
        userRepo.deleteById(id);
    }





}
