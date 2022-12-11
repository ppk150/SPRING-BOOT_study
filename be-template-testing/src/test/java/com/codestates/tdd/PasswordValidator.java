package com.codestates.tdd;

import java.util.regex.Pattern;

public class PasswordValidator {
    public void validate(String password){

//        boolean containSpecialChar = password.chars().anyMatch(ch -> !(Character.isDigit(ch)||Character.isAlphabetic(ch) ));
//
//        if(!containSpecialChar){
//            throw new RuntimeException("Invalid password");
//        }

        if(!Pattern.matches("(?=.*\\W)(?=\\S+$).+",password)){
            throw new RuntimeException("Invalid password");

        }

    }
}
