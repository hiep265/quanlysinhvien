package com.example.quanlysinhvienhaui.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int monHocID;
    private String tenMonHoc;

    @OneToOne(mappedBy = "monHoc", cascade = CascadeType.ALL, orphanRemoval = true)
    private HocPhan hocPhan;


}
