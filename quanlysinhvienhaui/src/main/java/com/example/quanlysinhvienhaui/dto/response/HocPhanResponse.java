package com.example.quanlysinhvienhaui.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Year;

@Data
@Getter
@Setter
public class HocPhanResponse {

    private int hocPhanID;
    private int hocKy;
    private int soTinChi;
    private Year namHoc;
    private LocalDate lichThi;
    private MonHocResponse monHocResponse;

}
