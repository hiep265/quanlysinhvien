package com.example.quanlysinhvienhaui.Service;

import com.example.quanlysinhvienhaui.entity.User;
import org.springframework.stereotype.Service;


public interface IUserService {
    public User RegisterUser(User user);
    public Boolean Login(User user) throws Exception;

}
