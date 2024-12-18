package com.example.quanlysinhvienhaui.controller;


import com.example.quanlysinhvienhaui.Service.hocphan.IHocPhanService;
import com.example.quanlysinhvienhaui.dto.request.ThemHocPhanRequest;
import com.example.quanlysinhvienhaui.dto.response.ApiResponse;
import com.example.quanlysinhvienhaui.dto.response.HocPhanResponse;
import com.example.quanlysinhvienhaui.entity.HocPhan;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hoc_phan")
@RequiredArgsConstructor
public class HocPhanController {
    private final IHocPhanService hocPhanService;

    @PostMapping("/add/{MonHocID}")
    public ResponseEntity<ApiResponse> ThemHocPhan(@PathVariable int MonHocID,@RequestBody ThemHocPhanRequest request){
        try {
            HocPhan hocPhan= hocPhanService.ThemMotHocPhan(MonHocID,  request);
            HocPhanResponse hocPhanResponse = hocPhanService.convertToDto(hocPhan);
            return ResponseEntity.ok().body(new ApiResponse("Thêm học phần thành công", hocPhanResponse));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Học phần môn này đã tồn tại", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> XemCacHocPhan (){
        List<HocPhanResponse> DS = hocPhanService.XemCacHocPhan();
        return ResponseEntity.ok().body(new ApiResponse("Danh sách học phần trong hệ thống", DS));
    }

    @PutMapping("add/lich_thi/{hocPhanID}")
    public ResponseEntity<ApiResponse> NhapLichThi(@PathVariable int hocPhanID,
                                                   @RequestParam LocalDate lichThi){
        try {
            HocPhanResponse hocPhanResponse = hocPhanService.NhapLichThi(hocPhanID, lichThi);
            return ResponseEntity.ok().body(new ApiResponse("Nhập lịch thi học phần thành công", hocPhanResponse));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
