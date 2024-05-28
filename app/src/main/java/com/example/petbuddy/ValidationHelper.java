package com.example.petbuddy;


import java.util.regex.Pattern;

public class ValidationHelper {
    //related to signup
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z]{2,8}" +
                    ")+"
    );

    public static boolean isValidEmail(String email) {
        return email != null && !email.isEmpty() && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
