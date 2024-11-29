package com.example.quanlysinhvienhaui.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "UserID")
    private int userId;
    @Column(name = "Username",nullable = false,unique = true)
    private String username;
    @Column(name = "Password",nullable = false)
    private String password;




}
