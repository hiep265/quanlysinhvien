package com.example.quanlysinhvienhaui.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.time.Year;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HocPhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hocPhanID;

    private int hocKy;

    private int soTinChi;

    private Year namHoc;



    @OneToOne
    @JoinColumn(name="monhoc_id")
    private MonHoc monHoc;

    @OneToMany(mappedBy = "hocPhan",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DangKy> dangKy;



}
