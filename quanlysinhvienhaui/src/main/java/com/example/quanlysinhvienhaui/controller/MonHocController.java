package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.MonHocService;
import com.example.quanlysinhvienhaui.entity.MonHoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/monhoc")
public class MonHocController {

    @Autowired
    private MonHocService monHocService;

    @GetMapping("/all")
    public ResponseEntity<List<MonHoc>> getAllMonHoc() {
        try {
            List<MonHoc> monHocs = monHocService.getAllMonHoc();
            return ResponseEntity.ok(monHocs);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonHoc> getMonHocById(@PathVariable String id) {
        try {
            MonHoc monHoc = monHocService.getMonHocById(id);
            return ResponseEntity.ok(monHoc);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}