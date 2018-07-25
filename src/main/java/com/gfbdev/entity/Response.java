package com.gfbdev.entity;

import com.gfbdev.utils.Constants;

/**
 * Created by Headtrap on 15/07/2017.
 */
public class Response<T> {

    public boolean status;
    public String message;
    public T data;

    private static Response response;


    public static Response error(String erro) {
        Response r = getInstance();
        r.setStatus(false);
        r.setMessage(erro);
        return r;
    }

    public static Response ok(Object o) {
        Response r = getInstance();
        r.setStatus(true);
        r.setData(o);
        return r;
    }
    public static Response ok(String message) {
        Response r = getInstance();
        r.setStatus(true);
        r.setMessage(message);
        return r;
    }


    public static Response getInstance(){
        if(response == null){
            response = new Response();
        }
        response.setData(null);
        response.setMessage(null);
        response.setStatus(false);
        return response;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        Response.response = response;
    }
}