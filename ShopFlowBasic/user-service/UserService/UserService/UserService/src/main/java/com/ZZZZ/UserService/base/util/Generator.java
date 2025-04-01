package com.ZZZZ.UserService.base.util;

import java.util.UUID;

public class Generator {
    public static String generatorUsername(){
        int desiredLength = 8;
        String random = UUID.randomUUID().toString().substring(0,desiredLength);
        return "username" + random.toUpperCase();
    }
}
