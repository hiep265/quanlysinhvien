package com.example.quanlysinhvienhaui.Service.dangky;

import com.example.quanlysinhvienhaui.dto.request.DangKyRequest;
import com.example.quanlysinhvienhaui.dto.request.NhapDiemRequest;
import com.example.quanlysinhvienhaui.dto.response.DangKyDto;
import com.example.quanlysinhvienhaui.dto.response.HocPhanDto;
import com.example.quanlysinhvienhaui.dto.response.MonHocDto;
import com.example.quanlysinhvienhaui.dto.response.UserDto;
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
        DangKy dangKy = new DangKy();
        dangKy.setTenGiaoVien(request.getTenGiaoVien());
        dangKy.setUser(user);
        dangKy.setHocPhan(hocPhan);
        dangKy.setThanhToan(false);
        user.setCongNo(user.getCongNo()+hocPhan.getSoTinChi()*600000);
        return dangKyRepository.save(dangKy);
    }
    @Override
    public List<DangKyDto> DangKyChuaThanhToan(int UserID){
        List<DangKy> DS =  dangKyRepository.findByUser_UserId(UserID);

               return DS.stream()
                .filter((dangKy) -> !dangKy.getThanhToan()).
                map((element) -> modelMapper.map(element, DangKyDto.class)).toList();
    }

    @Override
    public List<DangKyDto> DanhSachHocPhanDangKy(String username){
        User user = userRepository.findByUsername(username)
            .orElseThrow(()->new ResourceNotFoundException("Tên người dùng không hợp lệ"));
        List<DangKy> DS  = dangKyRepository.findByUser_Username(username);

        return DS.stream().map((element) -> modelMapper.map(element, DangKyDto.class)).toList();
    }

    @Override
    public void HuyDangKyHocPhan( int dangKyId) {
       dangKyRepository.findById(dangKyId).ifPresentOrElse(dangKyRepository::delete
               ,()-> new ResourceNotFoundException("Mã học phần không hợp lệ") );
    }
    @Override
    public DangKyDto nhapKetQuaHocPhan(int dangKyId, NhapDiemRequest request){
        DangKy dangKy = dangKyRepository.findById(dangKyId)
                .orElseThrow(()->new ResourceNotFoundException("Học phần đăng ký không hợp lệ"));
        dangKy.setTX1(request.getTX1());
        dangKy.setTX2(request.getTX2());
        dangKy.setDiem(request.getDiem());
        dangKyRepository.save(dangKy);
        return convertToDto(dangKy);
    }
    //public DangKyDto KetQuaHocPhan (int )

    @Override
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
