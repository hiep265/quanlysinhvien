package com.example.quanlysinhvienhaui.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "monhoc")
@Getter
@Setter
@NoArgsConstructor
public class MonHoc {
    @Id
    @Column(name = "MonHocID")
    private String monHocId;

    @Column(name = "TenMonHoc")
    private String tenMonHoc;

    @Column(name = "TinLyThuyet")
    private float tinLyThuyet;

    @Column(name = "TinThucHanh")
    private float tinThucHanh;

    @Column(name = "TinKhac")
    private float tinKhac;

    @Column(name = "TietLyThuyet")
    private int tietLyThuyet;

    @Column(name = "TietThucHanh")
    private int tietThucHanh;

    @Column(name = "TietKhac")
    private int tietKhac;
}
