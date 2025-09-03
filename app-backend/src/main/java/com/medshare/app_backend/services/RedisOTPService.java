package com.medshare.app_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisOTPService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveOTP(String phNumber, String otp){
        redisTemplate.opsForValue().set(phNumber, otp, Duration.ofMinutes(5));
    }

    public void retrieveOTP(String phNumber){
        redisTemplate.opsForValue().get(phNumber);
    }

    public void deleteOTP(String phNumber){
        redisTemplate.delete(phNumber);
    }
}
