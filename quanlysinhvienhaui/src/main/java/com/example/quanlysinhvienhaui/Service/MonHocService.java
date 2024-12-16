package com.example.quanlysinhvienhaui.Service;

import com.example.quanlysinhvienhaui.entity.MonHoc;
import com.example.quanlysinhvienhaui.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MonHocService implements IMonHoc{
    @Autowired
    private MonHocRepository monHocRepository;
    @Override
    public List<MonHoc> getAllMonHoc() throws Exception {
        try {
            return monHocRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Lỗi khi lấy danh sách môn học: " + e.getMessage());
        }
    }
    @Override
    public MonHoc getMonHocById(String id) throws Exception {
        try {
            Optional<MonHoc> optionalMonHoc = monHocRepository.findById(id);
            if (optionalMonHoc.isPresent()) {
                return optionalMonHoc.get();
            } else {
                throw new Exception("Không tìm thấy môn học với ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi lấy thông tin môn học: " + e.getMessage());
        }
    }

}
