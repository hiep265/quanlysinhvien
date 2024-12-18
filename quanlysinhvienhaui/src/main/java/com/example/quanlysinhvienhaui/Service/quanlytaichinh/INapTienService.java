package com.example.quanlysinhvienhaui.Service.quanlytaichinh;

import com.example.quanlysinhvienhaui.dto.request.NapTienRequest;
import com.example.quanlysinhvienhaui.dto.response.NapTienResponse;


public interface INapTienService {
    public NapTienResponse napTien(NapTienRequest request);
}
