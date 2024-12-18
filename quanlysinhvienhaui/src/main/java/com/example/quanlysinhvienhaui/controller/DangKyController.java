package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.dangky.IDangKyService;
import com.example.quanlysinhvienhaui.dto.request.DangKyRequest;
import com.example.quanlysinhvienhaui.dto.request.NhapDiemRequest;
import com.example.quanlysinhvienhaui.dto.response.ApiResponse;
import com.example.quanlysinhvienhaui.dto.response.DangKyResponse;
import com.example.quanlysinhvienhaui.entity.DangKy;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dang_ky")
@RequiredArgsConstructor
public class DangKyController {
    private final IDangKyService dangKyService;

    @PostMapping("/add")
    ResponseEntity<ApiResponse> DangKyHocPhan (@RequestParam String username,@RequestParam int hocPhanID,@RequestBody DangKyRequest request){
        try {
            DangKy dangKy= dangKyService.DangKyHocPhan(username, hocPhanID, request);
            DangKyResponse dangKyResponse = dangKyService.convertToDto(dangKy);

            return ResponseEntity.ok().body(new ApiResponse("Đăng ký 1 học phần thành công", dangKyResponse));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()+", Đăng ký học phần thất bại", null));
        }
    }
    @PostMapping("/add_result/{dangKyId}")
    ResponseEntity<ApiResponse> NhapKetQua (@PathVariable int dangKyId,@RequestBody NhapDiemRequest request) {
        try {
            DangKyResponse dangKyResponse = dangKyService.nhapKetQuaHocPhan(dangKyId, request);
            return ResponseEntity.ok().body(new ApiResponse("Nhập điểm cho học phần thành công", dangKyResponse));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage() + ", Nhập điểm học phần thất bại", null));

        }
    }
    @GetMapping("/lich_thi/{dangKyID}")
    ResponseEntity<ApiResponse> XemLichThi(@PathVariable int dangKyID){
        try{
            LocalDate lichThi = dangKyService.XemLichThi(dangKyID);
            return ResponseEntity.ok().body(new ApiResponse("Lịch thi của học phần này: ", lichThi));

        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/all/{userID}")
    ResponseEntity<ApiResponse> DSHPDangKy(@PathVariable int userID){
       try{
        List<DangKyResponse> dangKyResponses = dangKyService.DanhSachHocPhanDangKy(userID);
        return ResponseEntity.ok().body(new ApiResponse("Các học phần bạn đã đăng ký: ", dangKyResponses));

    }catch (ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
    }
    }

    @GetMapping("/HP_No/{userId}")
    ResponseEntity<ApiResponse> HPChuaThanhToan(@PathVariable int userId){
        List<DangKyResponse> DS  = dangKyService.DangKyChuaThanhToan(userId);
        if(!DS.isEmpty()) {
            return ResponseEntity.ok().body(new ApiResponse("Danh sách học phần chưa thanh toán", DS));
        }else{
            return ResponseEntity.ok().body(new ApiResponse("Bạn đã thanh toán tất cả học phần", null));
        }
    }

    @DeleteMapping("/delete/{dangKyId}")
    ResponseEntity<ApiResponse> HuyDangKy(@PathVariable int dangKyId ){
        try {
            dangKyService.HuyDangKyHocPhan(dangKyId);
            return ResponseEntity.ok().body(new ApiResponse("Hủy đăng ký học phần thành công", null));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
