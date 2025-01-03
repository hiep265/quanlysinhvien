package com.example.quanlysinhvienhaui.controller;

import com.example.quanlysinhvienhaui.Service.quanlytaichinh.INapTienService;
import com.example.quanlysinhvienhaui.dto.request.NapTienRequest;
import com.example.quanlysinhvienhaui.dto.response.ApiResponse;
import com.example.quanlysinhvienhaui.dto.response.NapTienResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/nap_tien")
public class NapTienController {

    private final INapTienService napTienService;

    @PostMapping("/recharge")
    public ResponseEntity<ApiResponse> NapTien (@RequestBody NapTienRequest request){
        try {
            NapTienResponse napTienResponse =  napTienService.napTien(request);
            return ResponseEntity.ok().body(new ApiResponse("Thực hiện nạp tiền: ", napTienResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
