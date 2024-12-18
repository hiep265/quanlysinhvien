package com.example.quanlysinhvienhaui.Service.monhoc;

import com.example.quanlysinhvienhaui.dto.request.ThemMonHocRequest;
import com.example.quanlysinhvienhaui.dto.response.MonHocDto;
import com.example.quanlysinhvienhaui.entity.MonHoc;

import java.util.List;

public interface IMonHocService {
    public MonHoc themMonHoc(ThemMonHocRequest request);


    List<MonHocDto> danhSachMonHoc();

    public MonHocDto getMonHocById(int id);

    MonHocDto convertToMonDto(MonHoc monHoc);
}
