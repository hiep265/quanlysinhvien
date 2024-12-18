package com.example.quanlysinhvienhaui.Service.hocphan;

import com.example.quanlysinhvienhaui.dto.request.ThemHocPhanRequest;
import com.example.quanlysinhvienhaui.dto.response.HocPhanDto;
import com.example.quanlysinhvienhaui.dto.response.MonHocDto;
import com.example.quanlysinhvienhaui.entity.HocPhan;
import com.example.quanlysinhvienhaui.entity.MonHoc;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import com.example.quanlysinhvienhaui.repository.HocPhanRepository;
import com.example.quanlysinhvienhaui.repository.MonHocRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class HocPhanService implements IHocPhanService {
    private final HocPhanRepository hocPhanRepository;

    private final ModelMapper modelMapper;
    private final MonHocRepository monHocRepository;
    @Override
    public List<HocPhanDto> XemCacHocPhan() {
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
    public HocPhanDto convertToDto(HocPhan hocPhan){
        HocPhanDto hocPhanDto  = modelMapper.map(hocPhan, HocPhanDto.class);

        MonHocDto monHoc = modelMapper.map(hocPhan.getMonHoc(), MonHocDto.class);
        hocPhanDto.setMonHocDto(monHoc);
        return hocPhanDto;
    }




}
