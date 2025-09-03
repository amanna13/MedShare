package com.medshare.app_backend.services;

import com.medshare.app_backend.entity.User;
import com.medshare.app_backend.repository.UserRepository;
import com.medshare.app_backend.security.AESUtility;
import com.medshare.app_backend.security.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthServices {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtility jwtUtility;

    public String register(User user){
        user.setPassword(AESUtility.encrypt(user.getPassword()));
        userRepository.save(user);
        return jwtUtility.generateToken(user.getUserName());
    }

    public String login (String userName, String password) {
        Optional<User> user = userRepository.findUserByUserName(userName);
        if (user.isEmpty()){
            return "Invalid Credentials";
        }

        User user1 = user.get();
        String decryptedPassword = AESUtility.decrypt(user1.getPassword());
        if(!password.equals(decryptedPassword)){
            return  " Invalid Credentials";
        }
        return jwtUtility.generateToken(user1.getUserName());
    }

}
