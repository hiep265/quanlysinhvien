package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.user.UserService;
import com.example.quanlysinhvienhaui.dto.response.ApiResponse;
import com.example.quanlysinhvienhaui.dto.response.UserDto;
import com.example.quanlysinhvienhaui.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registerUser = userService.RegisterUser(user);
        return ResponseEntity.ok().body(new ApiResponse("register success", null));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {

            UserDto login = userService.Login(user);
            if (login != null) {
                return ResponseEntity.ok().body(new ApiResponse("login success", login));
            } else {
                return ResponseEntity.status(401).body("invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }


    @GetMapping("/users")
    public ResponseEntity<ApiResponse> listUser() {
        List<UserDto> ds = userService.DSUser();
        return ResponseEntity.ok().body(new ApiResponse("Danh sách user: ", ds));
    }
}
