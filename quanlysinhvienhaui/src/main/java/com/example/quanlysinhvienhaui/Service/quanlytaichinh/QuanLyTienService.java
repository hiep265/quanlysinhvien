package com.example.quanlysinhvienhaui.Service.quanlytaichinh;

import com.example.quanlysinhvienhaui.dto.response.*;
import com.example.quanlysinhvienhaui.entity.DangKy;
import com.example.quanlysinhvienhaui.entity.User;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import com.example.quanlysinhvienhaui.repository.DangKyRepository;
import com.example.quanlysinhvienhaui.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuanLyTienService implements IQuanLyTienService{
    private final DangKyRepository dangKyRepository;
    private final ModelMapper modelMapper;


    private final UserRepository userRepository;

    @Override
    public QuanLyTienDto quanLyTien(int userId) {

        User user  = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("ID người dùng không hợp lệ"));
        int TongTinNo  = dangKyRepository.findByUser_UserId(userId).stream()
                .filter((dangKy)->(
                        !dangKy.getThanhToan()
                )).mapToInt((dangKy)->(
                    dangKy.getHocPhan().getSoTinChi()
                )).sum();
        float thanhToan = (float)TongTinNo*600000 - user.getSoDu();
        if(thanhToan<0){
            thanhToan = 0;
        }
        List<DangKyDto> dsDK =  dangKyRepository.findByUser_UserId(userId)
                .stream()
                .map(this::convertToDto).toList();
        return QuanLyTienDto.builder()
                .username(user.getUsername())
                .congNo((float)TongTinNo*600000)
                .soDu(user.getSoDu())
                .canThanhToan(thanhToan)
                .dangKyDtos(dsDK)
                .build();
    }

    public DangKyDto convertToDto(DangKy dangKy){
        DangKyDto dangKyDto  = modelMapper.map(dangKy, DangKyDto.class);
        UserDto userDto = modelMapper.map(dangKy.getUser(), UserDto.class);
        dangKyDto.setUser(userDto);
        MonHocDto monHocDto = modelMapper.map(dangKy.getHocPhan().getMonHoc(), MonHocDto.class);
        HocPhanDto hocPhanDto = modelMapper.map(dangKy.getHocPhan(), HocPhanDto.class);
        hocPhanDto.setMonHocDto(monHocDto);
        dangKyDto.setHocPhan(hocPhanDto);
        return dangKyDto;
    }


}
