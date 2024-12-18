package com.example.quanlysinhvienhaui.Service.hocphan;

import com.example.quanlysinhvienhaui.dto.request.ThemHocPhanRequest;
import com.example.quanlysinhvienhaui.dto.response.HocPhanResponse;
import com.example.quanlysinhvienhaui.entity.HocPhan;

import java.time.LocalDate;
import java.util.List;

public interface IHocPhanService {

    public List<HocPhanResponse> XemCacHocPhan ();

    public HocPhan ThemMotHocPhan(int MonHocID, ThemHocPhanRequest request);

    HocPhanResponse NhapLichThi(int hocPhanID, LocalDate lichThi);

    HocPhanResponse convertToDto(HocPhan hocPhan);
}
