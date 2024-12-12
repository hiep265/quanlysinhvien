package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.dangky.IDangKyService;
import com.example.quanlysinhvienhaui.dto.request.DangKyRequest;
import com.example.quanlysinhvienhaui.dto.request.NhapDiemRequest;
import com.example.quanlysinhvienhaui.dto.response.ApiResponse;
import com.example.quanlysinhvienhaui.dto.response.DangKyDto;
import com.example.quanlysinhvienhaui.entity.DangKy;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dang_ky")
@RequiredArgsConstructor
public class DangKyController {
    private final IDangKyService dangKyService;

    @PostMapping("/add")
    ResponseEntity<ApiResponse> DangKyHocPhan (@RequestParam String username,@RequestParam int hocPhanID,@RequestBody DangKyRequest request){
        try {
            DangKy dangKy= dangKyService.DangKyHocPhan(username, hocPhanID, request);
            DangKyDto dangKyDto = dangKyService.convertToDto(dangKy);

            return ResponseEntity.ok().body(new ApiResponse("Đăng ký 1 học phần thành công", dangKyDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.ok().body(new ApiResponse(e.getMessage()+", Đăng ký học phần thất bại", null));
        }
    }
    @PostMapping("/add_result/{dangKyId}")
    ResponseEntity<ApiResponse> NhapKetQua (@PathVariable int dangKyId,@RequestBody NhapDiemRequest request) {
        try {
            DangKyDto dangKyDto = dangKyService.nhapKetQuaHocPhan(dangKyId, request);
            return ResponseEntity.ok().body(new ApiResponse("Nhập điểm cho học phần thành công", dangKyDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.ok().body(new ApiResponse(e.getMessage() + ", Nhập điểm học phần thất bại", null));

        }
    }


}
