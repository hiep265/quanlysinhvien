package com.example.quanlysinhvienhaui.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "UserID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "Username",nullable = false,unique = true)
    private String username;
    @Column(name = "Password",nullable = false)
    private String password;



    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DangKy> dangKy;


}
