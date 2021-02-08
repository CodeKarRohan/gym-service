package com.learn.gym.Utility;

public class ValidationUtility {

    public static boolean isStringNullOrEmpty( String inputString) {
         return ( null == inputString
                 || inputString.trim().length() == 0) ? true : false;
    }
}
