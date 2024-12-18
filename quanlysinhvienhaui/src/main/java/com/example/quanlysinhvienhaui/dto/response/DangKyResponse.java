package com.example.quanlysinhvienhaui.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DangKyResponse {
    private int dangKyID;

    private String tenGiaoVien;

    private UserResponse user;

    private HocPhanResponse hocPhan;

    private float TX1;

    private float TX2;

    private float diem;

    private boolean thanhToan;



}
