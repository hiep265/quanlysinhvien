package com.example.quanlysinhvienhaui.dto.request;

import lombok.Data;

import java.time.Year;

@Data
public class ThemHocPhanRequest {
    private int hocKy;
    private int soTinChi;
    private Year namHoc;

}
