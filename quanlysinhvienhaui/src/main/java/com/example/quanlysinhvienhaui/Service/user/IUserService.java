package com.example.quanlysinhvienhaui.Service.user;

import com.example.quanlysinhvienhaui.dto.response.UserResponse;
import com.example.quanlysinhvienhaui.entity.User;

import java.util.List;


public interface IUserService {
    public User RegisterUser(User user);
    public UserResponse Login(User user) throws Exception;

    List<UserResponse> DSUser();
}
