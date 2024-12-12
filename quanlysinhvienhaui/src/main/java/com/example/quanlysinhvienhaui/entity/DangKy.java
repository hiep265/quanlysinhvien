package com.example.quanlysinhvienhaui.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DangKy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dangKyID;

    private String tenGiaoVien;

    private float TX1;

    private float TX2;

    private float diem;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="hocphan_id")
    private HocPhan hocPhan;
}
