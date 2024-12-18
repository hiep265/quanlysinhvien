package com.example.quanlysinhvienhaui.Service.quanlytaichinh;

import com.example.quanlysinhvienhaui.dto.response.QuanLyTienResponse;

public interface IQuanLyTienService {
 public QuanLyTienResponse quanLyTien(int userId);

    String ThanhToanHocPhan(int userID, int dangKyID);
}
