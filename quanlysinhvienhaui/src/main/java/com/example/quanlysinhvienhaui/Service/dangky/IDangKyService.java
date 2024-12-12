package com.example.quanlysinhvienhaui.Service.dangky;

import com.example.quanlysinhvienhaui.dto.request.DangKyRequest;
import com.example.quanlysinhvienhaui.dto.request.NhapDiemRequest;
import com.example.quanlysinhvienhaui.dto.response.DangKyDto;
import com.example.quanlysinhvienhaui.entity.DangKy;

import java.util.List;

public interface IDangKyService {
    public DangKy DangKyHocPhan (String username, int hocPhanID, DangKyRequest request);

    List<DangKyDto> DanhSachHocPhanDangKy(String username);

    public void HuyDangKyHocPhan (int dangKyId);

    DangKyDto nhapKetQuaHocPhan(int dangKyId, NhapDiemRequest request);

    DangKyDto convertToDto(DangKy dangKy);
}
