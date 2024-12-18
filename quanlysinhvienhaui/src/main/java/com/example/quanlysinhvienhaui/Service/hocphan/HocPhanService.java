package com.example.quanlysinhvienhaui.Service.hocphan;

import com.example.quanlysinhvienhaui.dto.request.ThemHocPhanRequest;
import com.example.quanlysinhvienhaui.dto.response.HocPhanResponse;
import com.example.quanlysinhvienhaui.dto.response.MonHocResponse;
import com.example.quanlysinhvienhaui.entity.HocPhan;
import com.example.quanlysinhvienhaui.entity.MonHoc;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import com.example.quanlysinhvienhaui.repository.HocPhanRepository;
import com.example.quanlysinhvienhaui.repository.MonHocRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class HocPhanService implements IHocPhanService {
    private final HocPhanRepository hocPhanRepository;

    private final ModelMapper modelMapper;
    private final MonHocRepository monHocRepository;
    @Override
    public List<HocPhanResponse> XemCacHocPhan() {
        return hocPhanRepository.findAll().stream().map((hocphan)->{
            return convertToDto(hocphan);
    }).toList();

    }


    @Override
    @Transactional
    public HocPhan ThemMotHocPhan(int MonHocID, ThemHocPhanRequest request) {

        MonHoc monHoc = monHocRepository.findById(MonHocID)
                .orElseThrow(()-> new ResourceNotFoundException("Mon hoc not found"));

        HocPhan hocPhan = new HocPhan();


        hocPhan.setMonHoc(monHoc);
        hocPhan.setNamHoc(request.getNamHoc());
        hocPhan.setHocKy(request.getHocKy());
        hocPhan.setSoTinChi(request.getSoTinChi());
        return hocPhanRepository.save(hocPhan);

    }

    @Override
    public HocPhanResponse NhapLichThi(int hocPhanID, LocalDate lichThi){
        HocPhan hocPhan = hocPhanRepository.findById(hocPhanID)
                .orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy học phần"));
        hocPhan.setLichThi(lichThi);
        hocPhanRepository.save(hocPhan);
        return convertToDto(hocPhan);
    }

    @Override
    public HocPhanResponse convertToDto(HocPhan hocPhan){
        HocPhanResponse hocPhanResponse = modelMapper.map(hocPhan, HocPhanResponse.class);

        MonHocResponse monHoc = modelMapper.map(hocPhan.getMonHoc(), MonHocResponse.class);
        hocPhanResponse.setMonHocResponse(monHoc);
        return hocPhanResponse;
    }




}
