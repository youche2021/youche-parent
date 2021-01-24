package com.youche.utils.springboot;

import org.springframework.boot.SpringApplication;

public class Main {

    private static Class applicationClazz;

    public static void run(Class clazz, String[] args) {
        applicationClazz = clazz;
        SpringApplication.run(clazz, args);
    }

    public static Class getApplicationClazz() {
        return applicationClazz;
    }

    public static String getApplicationClassName() {
        return applicationClazz.getName();
    }
}
