package com.example.quanlysinhvienhaui.Service.monhoc;

import com.example.quanlysinhvienhaui.dto.request.ThemMonHocRequest;
import com.example.quanlysinhvienhaui.dto.response.MonHocDto;
import com.example.quanlysinhvienhaui.entity.MonHoc;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import com.example.quanlysinhvienhaui.repository.MonHocRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MonHocService implements IMonHocService {
    private final ModelMapper modelMapper;
    private final MonHocRepository monHocRepository;
    @Override
    public MonHoc themMonHoc(ThemMonHocRequest request) {

        if(!monHocRepository.existsByTenMonHoc(request.getTenMonHoc())) {
            MonHoc monHoc = new MonHoc();
            monHoc.setTenMonHoc(request.getTenMonHoc());
            return monHocRepository.save(monHoc);
        }else{
            throw new ResourceNotFoundException("Môn học không hợp lệ, tên đã tồn tại");
        }
    }
    @Override
    public List<MonHocDto> danhSachMonHoc(){
        return monHocRepository.findAll()
                .stream()
                .map(this::convertToMonDto)
                .toList();
    }
    @Override
    public MonHocDto convertToMonDto(MonHoc monHoc){
        return modelMapper.map(monHoc, MonHocDto.class);
    }
}
