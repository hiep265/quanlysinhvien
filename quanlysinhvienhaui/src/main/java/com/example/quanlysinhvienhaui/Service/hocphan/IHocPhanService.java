package com.example.quanlysinhvienhaui.Service.hocphan;

import com.example.quanlysinhvienhaui.dto.request.ThemHocPhanRequest;
import com.example.quanlysinhvienhaui.dto.response.HocPhanDto;
import com.example.quanlysinhvienhaui.entity.HocPhan;

import java.util.List;

public interface IHocPhanService {

    public List<HocPhanDto> XemCacHocPhan ();

    public HocPhan ThemMotHocPhan(int MonHocID, ThemHocPhanRequest request);

    HocPhanDto convertToDto(HocPhan hocPhan);
}
