package com.gfbdev.utils;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class Password {
    private StrongPasswordEncryptor encryptor;

    private static Password ourInstance = new Password();

    public static Password getInstance() {
        return ourInstance;
    }

    private Password() {
    }

    public StrongPasswordEncryptor getEncryptor() {
        if (encryptor == null){
            encryptor = new StrongPasswordEncryptor();
        }
        return encryptor;
    }
}
