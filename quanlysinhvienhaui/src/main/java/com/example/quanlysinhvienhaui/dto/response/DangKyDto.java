package com.example.quanlysinhvienhaui.dto.response;

import lombok.Data;

@Data
public class DangKyDto {
    private int dangKyID;

    private String tenGiaoVien;

    private float TX1;

    private float TX2;

    private float diem;

    private UserDto user;

    private HocPhanDto hocPhan;
}
