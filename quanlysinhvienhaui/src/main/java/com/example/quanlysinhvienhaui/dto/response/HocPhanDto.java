package com.example.quanlysinhvienhaui.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Data
@Getter
@Setter
public class HocPhanDto {

    private int hocPhanID;
    private int hocKy;
    private int soTinChi;
    private Year namHoc;
    private MonHocDto monHocDto;

}
