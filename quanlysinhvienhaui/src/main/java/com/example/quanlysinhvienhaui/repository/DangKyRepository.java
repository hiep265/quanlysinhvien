package com.example.quanlysinhvienhaui.repository;

import com.example.quanlysinhvienhaui.entity.DangKy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DangKyRepository extends JpaRepository<DangKy, Integer> {
    List<DangKy> findByUser_Username(String username);
    List<DangKy> findByHocPhan_hocKy(int hocKy);
    List<DangKy> findByUser_UserId(int UserId);
}
