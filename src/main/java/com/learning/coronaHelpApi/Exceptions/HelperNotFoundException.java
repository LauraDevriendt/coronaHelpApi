package com.learning.coronaHelpApi.Exceptions;

public class HelperNotFoundException extends RuntimeException{
    private String tip;
    public HelperNotFoundException(String message, String tip) {
        super(message);
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }


}
