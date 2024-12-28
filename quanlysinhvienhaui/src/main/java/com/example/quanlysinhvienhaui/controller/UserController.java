package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.user.IUserService;
import com.example.quanlysinhvienhaui.Service.user.UserService;
import com.example.quanlysinhvienhaui.dto.response.ApiResponse;
import com.example.quanlysinhvienhaui.dto.response.JwtResponse;
import com.example.quanlysinhvienhaui.dto.response.UserResponse;
import com.example.quanlysinhvienhaui.entity.User;
import com.example.quanlysinhvienhaui.security.jwt.JwtUtils;
import com.example.quanlysinhvienhaui.security.user.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtUtils jwtUtils;
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registerUser = userService.RegisterUser(user);
        return ResponseEntity.ok().body(new ApiResponse("register success", null));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication =authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateTokenForUser(authentication);
            UserDetail userDetail = (UserDetail)authentication.getPrincipal();
            JwtResponse jwtResponse = new JwtResponse(userDetail.getId(), jwt);
            return ResponseEntity.ok(new ApiResponse("Login success", jwtResponse));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Login success failed", null));
        }

    }


    
    @GetMapping("/users")
    public ResponseEntity<ApiResponse> listUser() {
        List<UserResponse> ds = userService.DSUser();
        return ResponseEntity.ok().body(new ApiResponse("Danh sách user: ", ds));
    }
}
