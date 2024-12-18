package com.example.quanlysinhvienhaui.repository;

import com.example.quanlysinhvienhaui.entity.MonHoc;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MonHocRepository extends JpaRepository<MonHoc, Integer> {
    boolean  existsByTenMonHoc(String TenMonHoc);

}

