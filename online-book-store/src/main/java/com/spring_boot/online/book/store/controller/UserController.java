package com.spring_boot.online.book.store.controller;

import com.spring_boot.online.book.store.dto.UserDTO;
import com.spring_boot.online.book.store.service.impl.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private  final MyUserDetailsService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userDTO));
    }

}
