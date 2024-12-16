package com.example.quanlysinhvienhaui.Service.user;

import com.example.quanlysinhvienhaui.dto.response.UserDto;
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
    public UserDto Login(User user) throws Exception {
        try {
            Optional<User> optionalUser=userRepository.findByUsername(user.getUsername());
            if(optionalUser.isPresent()){
                User user1= optionalUser.get();
                if(user1.getPassword().equals(user.getPassword())){
                    return new UserDto(user1.getUserId(), user1.getUsername());
                }
            }else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(e);
        }

        return null;
    }
    @Override
    public List<UserDto> DSUser(){
        return  userRepository.findAll().stream().map((element) -> modelMapper.map(element, UserDto.class)).collect(Collectors.toList());
    }
}
