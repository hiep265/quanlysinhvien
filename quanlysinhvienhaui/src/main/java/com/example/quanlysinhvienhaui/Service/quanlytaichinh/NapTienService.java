package com.example.quanlysinhvienhaui.Service.quanlytaichinh;

import com.example.quanlysinhvienhaui.dto.request.NapTienRequest;
import com.example.quanlysinhvienhaui.dto.response.NapTienResponse;

import com.example.quanlysinhvienhaui.entity.User;
import com.example.quanlysinhvienhaui.exception.ResourceNotFoundException;
import com.example.quanlysinhvienhaui.repository.DangKyRepository;
import com.example.quanlysinhvienhaui.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@RequiredArgsConstructor
@Service
public class NapTienService implements INapTienService {
    private final DangKyRepository dangKyRepository;
    private final UserRepository userRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public NapTienResponse napTien(NapTienRequest request) {
        User user  = userRepository.findById(request.getUserID())
                .orElseThrow(()-> new ResourceNotFoundException("ID người dùng không hợp lệ"));
        int TongTinNo  = dangKyRepository.findByUser_UserId(request.getUserID()).stream()
                .filter((dangKy)->(
                        !dangKy.getThanhToan()
                )).mapToInt((dangKy)->(
                        dangKy.getHocPhan().getSoTinChi()
                )).sum();
        float SoTienCanThanhToan = TongTinNo*600000;

        try {
            Future<NapTienResponse> future = executorService.submit(()->xuLyThanhToan(request));

            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            return new NapTienResponse(null, "THẤT BẠI", "Lỗi trong quá trình giao dịch: " + e.getMessage());
        }
    }

    private NapTienResponse xuLyThanhToan(NapTienRequest request){
        try{
            xacThucThanhToan(request);
            Thread.sleep(3000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            return new NapTienResponse(null, "FAILED", "Thanh toán bị tạm dừng");
        }catch (IllegalArgumentException e){
            return new NapTienResponse(null, "FAILED", e.getMessage());
        }
        String maGiaoDich = UUID.randomUUID().toString();
        boolean paymentSuccess = Math.random() > 0.2;
        if(paymentSuccess){
            capNhatTaiKhoan(request);
            thongBao(request.getUserID(), "Nạp tiền thành công vào tài khoản");
            return new NapTienResponse(maGiaoDich, "THÀNH CÔNG", "Giao dịch thực hiện thành công");
        }else{
            thongBao(request.getUserID(), "Không nạp tiền thành công vào tài khoản");
            return new NapTienResponse(maGiaoDich, "THẤT BẠI", "Giao dịch thực hiện không thành công");
        }
    }

    private void xacThucThanhToan(NapTienRequest request){
        if(request.getAmount()<=0){
            throw new IllegalArgumentException("Số tiền thanh toán không hợp lệ");

        }

    }

    private void capNhatTaiKhoan(NapTienRequest request){
        User user  = userRepository.findById(request.getUserID())
                .orElseThrow(()-> new ResourceNotFoundException("ID người dùng không hợp lệ"));
        user.setSoDu(user.getSoDu() + request.getAmount());
        userRepository.save(user);

    }

    @Async
    private void thongBao(int userId, String message) {
        System.out.println("Thông báo nap tiền: " + userId + " - Lời nhắn: " + message);
    }
}
