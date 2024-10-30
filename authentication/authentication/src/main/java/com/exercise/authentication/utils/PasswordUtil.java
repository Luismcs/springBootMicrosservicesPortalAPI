package com.exercise.authentication.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hash(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
