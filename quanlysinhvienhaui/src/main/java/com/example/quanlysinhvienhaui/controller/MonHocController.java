package com.example.quanlysinhvienhaui.controller;


import com.example.quanlysinhvienhaui.dto.response.MonHocResponse;
import com.example.quanlysinhvienhaui.entity.MonHoc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quanlysinhvienhaui.Service.monhoc.IMonHocService;
import com.example.quanlysinhvienhaui.dto.request.ThemMonHocRequest;
import com.example.quanlysinhvienhaui.dto.response.ApiResponse;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/mon_hoc")
@AllArgsConstructor
public class MonHocController {
    private final IMonHocService monHocService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> ThemMonHoc (@RequestBody ThemMonHocRequest request){
        try {
            MonHoc monHoc =  monHocService.themMonHoc(request);
            MonHocResponse monHocResponse = monHocService.convertToMonDto(monHoc);
            return ResponseEntity.ok().body(new ApiResponse("Thêm một môn học thành công", monHocResponse));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMonHocById(@PathVariable int id) {
        try {
            MonHocResponse monHoc = monHocService.getMonHocById(id);
            return ResponseEntity.ok().body(new ApiResponse("Môn học "+monHoc.getMonHocID(), monHoc));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> DanhSachMonHoc(){
        List<MonHocResponse> DS =monHocService.danhSachMonHoc();
        return ResponseEntity.ok().body(new ApiResponse("Danh sách môn học", DS));
    }

}

