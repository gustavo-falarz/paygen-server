package com.gfbdev;


import java.util.ResourceBundle;

public class Messages {

    private static ResourceBundle instance;

    public static ResourceBundle getInstance() {
        if (instance == null){
            instance = ResourceBundle.getBundle("message");
        }
        return instance;
    }
}
