package com.aswebsiteapi.image;

public class ImageControllerValidationException extends RuntimeException {

    private final String field;

    public ImageControllerValidationException(String exceptionMessage, String field) {
        super(exceptionMessage);
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
