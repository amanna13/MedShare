package com.medshare.app_backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class AESUtility {
    private static final String ALGORITHM = "AES";

    @Value("${app.security.secret-key}")
    private static String SECRET_KEY;

    public static String encrypt(String data) {
        try{
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData);
        }catch (Exception e) {
            throw new RuntimeException("Error Encrypting Data", e);
        }
    }

    public static String decrypt(String encryptedData){
        try{
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            return new String(cipher.doFinal(decodedBytes));
        }catch (Exception e){
            throw new RuntimeException("Error Decrypting Data");
        }
    }
}
