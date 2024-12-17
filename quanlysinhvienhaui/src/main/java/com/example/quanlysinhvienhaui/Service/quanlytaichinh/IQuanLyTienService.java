package com.example.quanlysinhvienhaui.Service.quanlytaichinh;

import com.example.quanlysinhvienhaui.dto.response.QuanLyTienDto;

public interface IQuanLyTienService {
 public QuanLyTienDto quanLyTien(int userId);

    String ThanhToanHocPhan(int userID, int dangKyID);
}
