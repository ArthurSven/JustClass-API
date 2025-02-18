package com.devapps.justclass_api.utils.security;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecretKeyGenerator {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256"); // Or "HS512" for a larger key
        keyGenerator.init(256); // Key size (bits) - 256 or 512 are good choices
        java.security.Key secretKey = keyGenerator.generateKey();

        String encodedKey = Base64.getUrlEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Generated Secret Key (Base64URL encoded): " + encodedKey);
        // Copy the generated key from the console output.  This is what you'll use in your application.
    }
}