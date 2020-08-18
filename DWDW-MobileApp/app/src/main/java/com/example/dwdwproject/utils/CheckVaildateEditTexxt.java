package com.example.dwdwproject.utils;

import java.util.regex.Pattern;

public class CheckVaildateEditTexxt {
    public static boolean checkEditTextNull(String editString){
        if (editString.trim().length() > 0)
            return false;
        return true;
    }
    public static  boolean checkValidatePhone(String phone){
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 9 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
                // txtPhone.setError("Not Valid Number");
            } else {
                check = android.util.Patterns.PHONE.matcher(phone).matches();
            }
        } else {
            check = false;
        }
        return check;

    }
}
