package com.example.quanlysinhvienhaui.Service.dangky;

import com.example.quanlysinhvienhaui.dto.response.KetQuaResponse;

import java.util.List;

public interface IKetQuaService {
    KetQuaResponse KetQuaMotHocPhan(int DangKyId, int UserId);
    List<KetQuaResponse> KetQuaMotHocKy(int HocKy, int UserId);
    float GPA(int UserId);
}
