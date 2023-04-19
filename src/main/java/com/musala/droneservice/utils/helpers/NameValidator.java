package com.musala.droneservice.utils.helpers;

public class NameValidator {

    public static boolean isValidName(String name) {
            String regex = "[a-zA-Z_0-9-]+";
            return name.matches(regex);
        }
    }


