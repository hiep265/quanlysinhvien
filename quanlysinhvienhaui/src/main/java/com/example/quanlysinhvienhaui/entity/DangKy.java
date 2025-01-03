package com.example.quanlysinhvienhaui.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="hocphan_id")
    private HocPhan hocPhan;

    private float TX1;

    private float TX2;

    private float diem;

    private boolean thanhToan;



    public boolean getThanhToan() {
        return this.thanhToan;
    }
}


