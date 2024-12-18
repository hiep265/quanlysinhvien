package com.example.quanlysinhvienhaui.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KetQuaResponse {
    private String userName;

    private String tenHocPhan;

    private String tenGiaoVien;

    private float TX1;

    private float TX2;

    private float diem;

    private float tongKet;
}
