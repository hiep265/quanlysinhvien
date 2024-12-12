package com.example.quanlysinhvienhaui.Service.user;

import com.example.quanlysinhvienhaui.entity.User;


public interface IUserService {
    public User RegisterUser(User user);
    public Boolean Login(User user) throws Exception;

}
