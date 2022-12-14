package com.app.clinicon.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

import java.util.Base64;


@Component
public class TextUtils {

    
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final Base64.Encoder BASE64_ENCODER = Base64.getUrlEncoder();

    public String generateSecureToken(int length){

        byte[] randomBytes = new byte[24];
        SECURE_RANDOM.nextBytes(randomBytes);
        return BASE64_ENCODER.encodeToString(randomBytes).substring(0, length++);
    }
    
}
