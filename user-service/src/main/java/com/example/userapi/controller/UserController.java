package com.example.userapi.controller;

import com.example.userapi.dto.UserDto;
import com.example.userapi.entity.User;
import com.example.userapi.service.UserServices;
import jakarta.websocket.server.PathParam;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping
    public List<UserDto> findAll(){
        return userServices.findALl();
    }

    @PostMapping
    public User add(@RequestBody User user){
        return userServices.add(user);
    }

    @PutMapping("/deactivate/{username}")
    public UserDto deactivate(@PathVariable String username){
        return userServices.deactivate(username);
    }

    @PutMapping("/activate/{username}")
    public UserDto activate(@PathVariable String username){
        return userServices.activate(username);
    }

}
