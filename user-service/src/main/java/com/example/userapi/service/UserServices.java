package com.example.userapi.service;
import com.example.userapi.dto.UserDto;
import com.example.userapi.dto.UserMapper;
import com.example.userapi.entity.User;
import com.example.userapi.exception.CustomException;
import com.example.userapi.exception.ExceptionMessage;
import com.example.userapi.repository.AuthorityRepository;
import com.example.userapi.repository.UserRepository;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;


    public List<UserDto> findALl(){
        List<User> users = userRepository.findAll();
        return users.stream().map((user)->userMapper.toDTO(user)).collect(Collectors.toList());

    }


    public User add(User user){

        if (userRepository.countUsersByUsername(user.getUsername())!=0){
            throw new CustomException(ExceptionMessage.Name_Already_Exist);
        }
        user.setPassword("{noop}"+user.getPassword());
        User savedUser = userRepository.save(user);
        authorityRepository.save(user.getAuthority().get(0));
        return savedUser;
    }

    public UserDto deactivate(String username){
        User user = userRepository.findById(username).orElseThrow(()->new CustomException(ExceptionMessage.User_Not_Found));

        user.setEnabled((short) 0);
        return userMapper.toDTO(userRepository.save(user));
    }
    public UserDto activate(String username){
        User user = userRepository.findById(username).orElseThrow(()->new CustomException(ExceptionMessage.User_Not_Found));

        user.setEnabled((short) 1);
        return userMapper.toDTO(userRepository.save(user));
    }

}
