package com.example.quanlysinhvienhaui.Service.monhoc;

import com.example.quanlysinhvienhaui.dto.request.ThemMonHocRequest;
import com.example.quanlysinhvienhaui.dto.response.MonHocResponse;
import com.example.quanlysinhvienhaui.entity.MonHoc;

import java.util.List;

public interface IMonHocService {
    public MonHoc themMonHoc(ThemMonHocRequest request);


    List<MonHocResponse> danhSachMonHoc();

    public MonHocResponse getMonHocById(int id);

    MonHocResponse convertToMonDto(MonHoc monHoc);
}
