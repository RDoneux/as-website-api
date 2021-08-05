package com.aswebsiteapi.util;

public class ValidationError {

    private final String field;
    private final String error;

    public ValidationError(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }

}
