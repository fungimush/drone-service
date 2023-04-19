package com.musala.droneservice.utils.helpers;

public class CodeValidator {

    public static boolean isValidCode(String code) {
        String regex = "[A-Z0-9_]+";
        return code.matches(regex);
    }
}
