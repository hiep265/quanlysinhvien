package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.dangky.IKetQuaService;
import com.example.quanlysinhvienhaui.dto.response.ApiResponse;
import com.example.quanlysinhvienhaui.dto.response.KetQuaResponse;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ket_qua")
public class KetQuaController {
    private final IKetQuaService ketQuaService;

    @GetMapping("/ket_qua_1_hp/{DangKyId}/{UserId}")
    ResponseEntity<ApiResponse> KetQuaMotHocPhan(@PathVariable int DangKyId ,@PathVariable int UserId ){
        try {
            KetQuaResponse ketQuaResponse = ketQuaService.KetQuaMotHocPhan(DangKyId, UserId);
            return ResponseEntity.ok().body(new ApiResponse("Kết quả của học phần: ", ketQuaResponse));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }

    }
    @GetMapping("/ket_qua_1_hoc_ky/{HocKy}/{UserId}")
    ResponseEntity<ApiResponse> KetQuaMotHocKy(@PathVariable int HocKy ,@PathVariable int UserId ){
        try {
            List<KetQuaResponse> dsketQuaResponse = ketQuaService.KetQuaMotHocKy(HocKy, UserId);
            return ResponseEntity.ok().body(new ApiResponse("Kết quả của học kỳ: ", dsketQuaResponse));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/ket_qua_GPA/{UserId}")
    ResponseEntity<ApiResponse> KetQuaGPA(@PathVariable int UserId ){
        try {
            float GPA  = ketQuaService.GPA(UserId);
            return ResponseEntity.ok().body(new ApiResponse("Kết quả của GPA: ", GPA));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }






}
