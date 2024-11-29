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
    public Boolean Login(User user) throws Exception {
        try {

            Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());


            if (optionalUser.isPresent()) {
                User foundUser = optionalUser.get();
                return foundUser.getPassword().equals(user.getPassword());
            }


            return false;

        } catch (Exception e) {

            throw new Exception("Lỗi xảy ra trong quá trình đăng nhập: " + e.getMessage(), e);
        }
    }

}
