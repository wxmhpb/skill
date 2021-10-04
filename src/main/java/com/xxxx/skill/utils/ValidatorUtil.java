package com.xxxx.skill.utils;


import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern MOBLIE_PATTERN=Pattern.compile("[1][3-9][0-9]9$");

    public static boolean isMoblie(String moblie){
        if(StringUtils.isEmpty(moblie)){
            return false;
        }
        Matcher matcher=MOBLIE_PATTERN.matcher(moblie);
        return matcher.matches();
    }

}
