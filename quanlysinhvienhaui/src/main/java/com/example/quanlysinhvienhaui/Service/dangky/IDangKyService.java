package com.example.quanlysinhvienhaui.Service.dangky;

import com.example.quanlysinhvienhaui.dto.request.DangKyRequest;
import com.example.quanlysinhvienhaui.dto.request.NhapDiemRequest;
import com.example.quanlysinhvienhaui.dto.response.DangKyResponse;
import com.example.quanlysinhvienhaui.entity.DangKy;

import java.time.LocalDate;
import java.util.List;

public interface IDangKyService {
    public DangKy DangKyHocPhan (String username, int hocPhanID, DangKyRequest request);

    List<DangKyResponse> DangKyChuaThanhToan(int UserID);

    List<DangKyResponse> DanhSachHocPhanDangKy(int UserID);


    LocalDate XemLichThi(int dangKyID);

    public void HuyDangKyHocPhan (int dangKyId);

    DangKyResponse nhapKetQuaHocPhan(int dangKyId, NhapDiemRequest request);

    DangKyResponse convertToDto(DangKy dangKy);
}
