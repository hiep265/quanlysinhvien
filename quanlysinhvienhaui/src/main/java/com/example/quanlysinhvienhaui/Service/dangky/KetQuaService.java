package com.example.quanlysinhvienhaui.Service.dangky;

import com.example.quanlysinhvienhaui.dto.response.KetQuaDto;
import com.example.quanlysinhvienhaui.entity.DangKy;
import com.example.quanlysinhvienhaui.entity.User;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import com.example.quanlysinhvienhaui.repository.DangKyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KetQuaService implements IKetQuaService{
    private final DangKyRepository dangKyRepository;
    @Override
    public KetQuaDto KetQuaMotHocPhan(int DangKyId, int UserId) {
        DangKy dangKy = dangKyRepository.findById(DangKyId)
                .orElseThrow(()->new ResourceNotFoundException("Mã học phần đăng ký không hợp lệ!!!"));
        String tenHocPhan = dangKy.getHocPhan().getMonHoc().getTenMonHoc();
                float TK = (float) (dangKy.getTX1()*0.2 + dangKy.getTX2()*0.2 + dangKy.getDiem()*0.6);
        return KetQuaDto.builder()
                .tenGiaoVien(dangKy.getTenGiaoVien())
                .tenHocPhan(tenHocPhan)
                .userName(dangKy.getUser().getUsername())
                .TX1(dangKy.getTX1())
                .TX2(dangKy.getTX2())
                .diem(dangKy.getDiem())
                .tongKet(TK)
                .build();

    }

    @Override
    public List<KetQuaDto> KetQuaMotHocKy(int HocKy, int UserId) {


        return dangKyRepository.findByHocPhan_hocKy(HocKy)
               .stream().filter((dangKy)->
                   dangKy.getUser().getUserId()==UserId)
                .map(dangKy -> {
                    float TK = (float) (dangKy.getTX1()*0.2 + dangKy.getTX2()*0.2 + dangKy.getDiem()*0.6);
                    return KetQuaDto.builder()
                            .tenGiaoVien(dangKy.getTenGiaoVien())
                            .tenHocPhan(dangKy.getHocPhan().getMonHoc().getTenMonHoc())
                            .userName(dangKy.getUser().getUsername())
                            .TX1(dangKy.getTX1())
                            .TX2(dangKy.getTX2())
                            .diem(dangKy.getDiem())
                            .tongKet(TK)
                            .build();
                })
                .toList();
     }

    @Override
    public float GPA(int UserId) {
        List<DangKy> dsdangKy = dangKyRepository.findByUser_UserId(UserId);

        List<KetQuaDto> ketQuaDtos = dsdangKy.stream()
                .map(dangKy -> {
                    float TK = (float) (dangKy.getTX1()*0.2 + dangKy.getTX2()*0.2 + dangKy.getDiem()*0.6);
                    return KetQuaDto.builder()
                            .tenGiaoVien(dangKy.getTenGiaoVien())
                            .tenHocPhan(dangKy.getHocPhan().getMonHoc().getTenMonHoc())
                            .userName(dangKy.getUser().getUsername())
                            .TX1(dangKy.getTX1())
                            .TX2(dangKy.getTX2())
                            .diem(dangKy.getDiem())
                            .tongKet(TK)
                            .build();
                })
                .toList();

        double total = ketQuaDtos.stream().filter(ketQuaDto -> ketQuaDto.getTongKet()>=4)
                .mapToDouble(KetQuaDto::getTongKet
                ).average().orElse(0);


        return (float) total;
    }
}
