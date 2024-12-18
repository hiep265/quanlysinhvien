package com.example.quanlysinhvienhaui.Service.dangky;

import com.example.quanlysinhvienhaui.dto.request.DangKyRequest;
import com.example.quanlysinhvienhaui.dto.request.NhapDiemRequest;
import com.example.quanlysinhvienhaui.dto.response.DangKyResponse;
import com.example.quanlysinhvienhaui.dto.response.HocPhanResponse;
import com.example.quanlysinhvienhaui.dto.response.MonHocResponse;
import com.example.quanlysinhvienhaui.dto.response.UserResponse;
import com.example.quanlysinhvienhaui.entity.DangKy;
import com.example.quanlysinhvienhaui.entity.HocPhan;
import com.example.quanlysinhvienhaui.entity.User;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import com.example.quanlysinhvienhaui.repository.DangKyRepository;
import com.example.quanlysinhvienhaui.repository.HocPhanRepository;
import com.example.quanlysinhvienhaui.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DangKyService implements IDangKyService{
    private final UserRepository userRepository;
    private final HocPhanRepository hocPhanRepository;
    private final DangKyRepository dangKyRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public DangKy DangKyHocPhan(String username, int hocPhanID, DangKyRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new ResourceNotFoundException("Tên người dùng không hợp lệ"));
        HocPhan hocPhan = hocPhanRepository.findById(hocPhanID)
                .orElseThrow(()->new ResourceNotFoundException("Mã học phần không hợp lệ"));
        List<DangKy> olddangKy=dangKyRepository.findByUser_UserId(user.getUserId())
                .stream().filter(dangKy -> dangKy.getHocPhan().getHocPhanID()==hocPhanID).toList();
        if(olddangKy.isEmpty()) {
            DangKy dangKy = new DangKy();
            dangKy.setTenGiaoVien(request.getTenGiaoVien());
            dangKy.setUser(user);
            dangKy.setHocPhan(hocPhan);
            dangKy.setThanhToan(false);
            user.setCongNo(user.getCongNo() + hocPhan.getSoTinChi() * 600000);
            return dangKyRepository.save(dangKy);
        }else{
            throw new ResourceNotFoundException("Bạn đã đăng ký học phần này rồi");
        }
    }
    @Override
    public List<DangKyResponse> DangKyChuaThanhToan(int UserID){
        List<DangKy> DS =  dangKyRepository.findByUser_UserId(UserID);

               return DS.stream()
                .filter((dangKy) -> !dangKy.getThanhToan()).
                map((element) -> modelMapper.map(element, DangKyResponse.class)).toList();
    }

    @Override
    public List<DangKyResponse> DanhSachHocPhanDangKy(int userID){
        User user = userRepository.findById(userID)
            .orElseThrow(()->new ResourceNotFoundException("Người dùng không hợp lệ"));
        List<DangKy> DS  = dangKyRepository.findByUser_UserId(userID);

        return DS.stream().map(this::convertToDto).toList();
    }

    @Override
    public LocalDate XemLichThi(int dangKyID){
        DangKy dangKy = dangKyRepository.findById(dangKyID)
                .orElseThrow(()->new ResourceNotFoundException("Không tìm thấy học phần đăng ký"));

        LocalDate lichThi = dangKy.getHocPhan().getLichThi();
        if(lichThi== null){
            throw new ResourceNotFoundException("Chưa có lịch thi");
        }else{
            return lichThi;
        }
    }


    @Override
    public void HuyDangKyHocPhan( int dangKyId) {
       dangKyRepository.findById(dangKyId).ifPresentOrElse(dangKyRepository::delete
               ,()-> new ResourceNotFoundException("Mã học phần không hợp lệ") );
    }
    @Override
    public DangKyResponse nhapKetQuaHocPhan(int dangKyId, NhapDiemRequest request){
        DangKy dangKy = dangKyRepository.findById(dangKyId)
                .orElseThrow(()->new ResourceNotFoundException("Học phần đăng ký không hợp lệ"));
        dangKy.setTX1(request.getTX1());
        dangKy.setTX2(request.getTX2());
        dangKy.setDiem(request.getDiem());
        dangKyRepository.save(dangKy);
        return convertToDto(dangKy);
    }
    //public DangKyResponse KetQuaHocPhan (int )

    @Override
    public DangKyResponse convertToDto(DangKy dangKy){
            DangKyResponse dangKyResponse = modelMapper.map(dangKy, DangKyResponse.class);
            UserResponse userResponse = modelMapper.map(dangKy.getUser(), UserResponse.class);
            dangKyResponse.setUser(userResponse);
        HocPhanResponse hocPhanResponse = modelMapper.map(dangKy.getHocPhan(), HocPhanResponse.class);
        MonHocResponse monHocResponse = modelMapper.map(dangKy.getHocPhan().getMonHoc(), MonHocResponse.class);
        hocPhanResponse.setMonHocResponse(monHocResponse);

        dangKyResponse.setHocPhan(hocPhanResponse);
            return dangKyResponse;
    }



}
