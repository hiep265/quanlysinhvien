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
    @Override
    public String ThanhToanHocPhan(int userID, int dangKyID){
        User user = userRepository.findById(userID)
                .orElseThrow(()->new ResourceNotFoundException(" Có lỗi khi tìm người dùng"));
        DangKy dangKy = dangKyRepository.findById(dangKyID)
                .orElseThrow(()->new ResourceNotFoundException(" Có lỗi khi tìm học phần"));
        if(dangKy.getThanhToan()){
            return "Học phần này đã đươc thanh toán rồi";
        }
        float soDu = user.getSoDu()-(dangKy.getHocPhan().getSoTinChi()*600000);
        if(soDu<0){
            return "Tiền trong tài khoản không đủ hãy nạp thêm";
        }
        user.setSoDu(soDu);
        float CongNo = user.getCongNo()-(dangKy.getHocPhan().getSoTinChi()*600000);
        if(CongNo < 0 ){
            CongNo = 0;
        }
        user.setCongNo(CongNo);
        dangKy.setThanhToan(true);
        dangKyRepository.save(dangKy);
        userRepository.save(user);

        return "Thanh toán học phần "+dangKy.getDangKyID()+" thành công";

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