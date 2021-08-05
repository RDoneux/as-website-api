package com.aswebsiteapi.util;

import java.util.List;

public class DefaultExceptionResponse<T> {

    private String message;
    private List<?> errors;

    public DefaultExceptionResponse(String message, List<T> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<?> getErrors() {
        return errors;
    }

}
