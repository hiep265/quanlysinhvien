package com.example.quanlysinhvienhaui.Service;

import com.example.quanlysinhvienhaui.entity.MonHoc;

import java.util.List;

public interface IMonHoc {
    public List<MonHoc> getAllMonHoc() throws Exception;
    public MonHoc getMonHocById(String id) throws Exception;

}
