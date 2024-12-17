package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.UserService;
import com.example.quanlysinhvienhaui.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> login(@RequestBody User user) {
        try {

            User loggedInUser = userService.Login(user);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("user", Map.of(
                    "userId", loggedInUser.getUserId(),
                    "username", loggedInUser.getUsername()
            ));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", e.getMessage()
            ));
        }
    }


}
