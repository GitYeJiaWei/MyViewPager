package com.example.yjw.Exception;


public class ApiException extends BaseException {


    public ApiException(boolean code, String displayMessage) {
        super(code, displayMessage);
    }
}
