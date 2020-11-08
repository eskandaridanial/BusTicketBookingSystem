package com.company.authService;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {

    public String genSelector(){
        return RandomStringUtils.randomAlphanumeric(10);
    }

    public String genValidator(){
        return RandomStringUtils.randomAlphanumeric(20);
    }
}
