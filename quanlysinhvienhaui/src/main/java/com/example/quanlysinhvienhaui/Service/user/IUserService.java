package com.example.quanlysinhvienhaui.Service.user;

import com.example.quanlysinhvienhaui.dto.response.UserDto;
import com.example.quanlysinhvienhaui.entity.User;

import java.util.List;


public interface IUserService {
    public User RegisterUser(User user);
    public UserDto Login(User user) throws Exception;

    List<UserDto> DSUser();
}
