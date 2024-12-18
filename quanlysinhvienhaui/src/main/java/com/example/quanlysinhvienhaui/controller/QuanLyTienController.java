package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.quanlytaichinh.IQuanLyTienService;
import com.example.quanlysinhvienhaui.dto.response.ApiResponse;
import com.example.quanlysinhvienhaui.dto.response.QuanLyTienDto;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tien")
@RequiredArgsConstructor
public class QuanLyTienController {
    private final IQuanLyTienService quanLyTienService;

    @GetMapping("/quan_ly/{userID}")
    ResponseEntity<ApiResponse> QuanLyTien(@PathVariable int userID){
        try {
            QuanLyTienDto quanLyTienDto = quanLyTienService.quanLyTien(userID);
            return ResponseEntity.ok().body( new ApiResponse("Thông tin tài chính: ", quanLyTienDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.ok().body( new ApiResponse("Người dùng không hợp lệ", null));
        }

    }

    @PutMapping("/thanh_toan_hp_no/{userId}/{dangKyId}")
    ResponseEntity<ApiResponse> ThanhToanHocPhanConNo(@PathVariable int userId, @PathVariable int dangKyId){
        try {
            String mess = quanLyTienService.ThanhToanHocPhan(userId, dangKyId);
            return ResponseEntity.ok().body(new ApiResponse("Thanh toán học phần còn nợ", mess ));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.ok().body(new ApiResponse(e.getMessage(), null));
        }
    }
}
