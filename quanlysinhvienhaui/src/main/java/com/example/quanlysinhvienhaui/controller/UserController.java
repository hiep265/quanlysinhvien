package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.UserService;
import com.example.quanlysinhvienhaui.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
       User registerUser= userService.RegisterUser(user);
        return ResponseEntity.ok("success");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws Exception {
        boolean login=userService.Login(user);
        if(login){
            return ResponseEntity.ok().body("login success");
        }else {
            return ResponseEntity.status(401).body("invalid username or password");
        }

    }
}
