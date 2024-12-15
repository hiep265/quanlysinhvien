package com.example.quanlysinhvienhaui.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuanLyTienDto {
    private String username;
    private float soDu;
    private float congNo;
    private float canThanhToan;
    private List<DangKyDto> dangKyDtos;

    public void updateThanhToan(){
        this.canThanhToan  = this.congNo - this.soDu;
    }
}
