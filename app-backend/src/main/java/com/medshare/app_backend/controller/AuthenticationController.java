package com.medshare.app_backend.controller;

import com.medshare.app_backend.entity.LoginRequest;
import com.medshare.app_backend.entity.User;
import com.medshare.app_backend.repository.UserRepository;
import com.medshare.app_backend.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthServices authServices;

    @Autowired
    private UserRepository userRepository;




    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        if (userRepository.existsUserByUserName(user.getUserName())){
            return new ResponseEntity<>( "Username Taken", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(authServices.register(user), HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authServices.login(loginRequest.getUserName(), loginRequest.getPassword()), HttpStatus.OK);
    }


}
