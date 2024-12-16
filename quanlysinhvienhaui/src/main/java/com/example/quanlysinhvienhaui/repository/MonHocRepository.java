package com.example.quanlysinhvienhaui.repository;

import com.example.quanlysinhvienhaui.entity.MonHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonHocRepository extends JpaRepository<MonHoc,String> {
    @Override
    Page<MonHoc> findAll(Pageable pageable);

}
