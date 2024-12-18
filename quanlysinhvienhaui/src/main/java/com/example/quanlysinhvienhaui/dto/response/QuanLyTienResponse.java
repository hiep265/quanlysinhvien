package com.example.quanlysinhvienhaui.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuanLyTienResponse {
    private String username;
    private float soDu;
    private float congNo;
    private float canThanhToan;
    private List<DangKyResponse> dangKyResponses;

    public void updateThanhToan(){
        this.canThanhToan  = this.congNo - this.soDu;
    }
}
