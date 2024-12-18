package com.example.quanlysinhvienhaui.Service.user;

import com.example.quanlysinhvienhaui.dto.response.UserResponse;
import com.example.quanlysinhvienhaui.entity.User;
import com.example.quanlysinhvienhaui.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User RegisterUser(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public UserResponse Login(User user) throws Exception {

            // Tìm kiếm người dùng theo username
            Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());

            if (optionalUser.isPresent()) {
                User foundUser = optionalUser.get();
                // Kiểm tra mật khẩu
                if (foundUser.getPassword().equals(user.getPassword())) {
                    return modelMapper.map(foundUser, UserResponse.class); // Đăng nhập thành công
                } else {
                    throw new Exception("Sai mật khẩu!"); // Thông báo sai mật khẩu
                }
            } else {
                throw new Exception("Người dùng không tồn tại!"); // Thông báo người dùng không tồn tại
            }

    }


    @Override
    public List<UserResponse> DSUser() {
        return userRepository.findAll().stream().map((element) -> modelMapper.map(element, UserResponse.class)).collect(Collectors.toList());
    }

}