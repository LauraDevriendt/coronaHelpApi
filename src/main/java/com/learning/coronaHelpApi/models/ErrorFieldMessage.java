package com.learning.coronaHelpApi.models;

public class ErrorFieldMessage {
    private String code;
    private String field;
    private String message;

    public ErrorFieldMessage(String code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }
}
