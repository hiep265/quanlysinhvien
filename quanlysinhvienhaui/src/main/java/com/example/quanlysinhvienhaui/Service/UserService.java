package com.example.quanlysinhvienhaui.Service;

import com.example.quanlysinhvienhaui.entity.User;
import com.example.quanlysinhvienhaui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User RegisterUser(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User Login(User user) throws Exception {
        try {
            // Tìm kiếm người dùng theo username
            Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());

            if (optionalUser.isPresent()) {
                User foundUser = optionalUser.get();
                // Kiểm tra mật khẩu
                if (foundUser.getPassword().equals(user.getPassword())) {
                    return foundUser; // Đăng nhập thành công
                } else {
                    throw new Exception("Sai mật khẩu!"); // Thông báo sai mật khẩu
                }
            } else {
                throw new Exception("Người dùng không tồn tại!"); // Thông báo người dùng không tồn tại
            }
        } catch (Exception e) {
            // Ném ngoại lệ với thông báo chi tiết
            throw new Exception("Lỗi xảy ra trong quá trình đăng nhập: " + e.getMessage(), e);
        }
    }


}
