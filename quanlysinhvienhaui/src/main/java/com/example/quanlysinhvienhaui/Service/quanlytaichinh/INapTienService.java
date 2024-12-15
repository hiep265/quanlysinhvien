package com.example.quanlysinhvienhaui.Service.quanlytaichinh;

import com.example.quanlysinhvienhaui.dto.request.NapTienRequest;
import com.example.quanlysinhvienhaui.dto.response.NapTienDto;


public interface INapTienService {
    public NapTienDto napTien(NapTienRequest request);
}
