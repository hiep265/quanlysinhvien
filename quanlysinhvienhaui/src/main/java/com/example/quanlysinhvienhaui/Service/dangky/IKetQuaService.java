package com.example.quanlysinhvienhaui.Service.dangky;

import com.example.quanlysinhvienhaui.dto.response.DangKyDto;
import com.example.quanlysinhvienhaui.dto.response.KetQuaDto;

import java.util.List;

public interface IKetQuaService {
    KetQuaDto KetQuaMotHocPhan(int DangKyId, int UserId);
    List<KetQuaDto> KetQuaMotHocKy(int HocKy, int UserId);
    float GPA(int UserId);
}
