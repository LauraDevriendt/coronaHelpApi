package com.learning.coronaHelpApi.models;

public class ErrorMessage {
    private Integer code;
    private String Message;
    private String tip;


    public ErrorMessage(Integer code, String message, String tip) {
        this.code = code;
        Message = message;
        this.tip = tip;
    }
}
