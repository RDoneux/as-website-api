package com.aswebsiteapi.image;

import java.util.List;

import com.aswebsiteapi.util.DefaultExceptionResponse;
import com.aswebsiteapi.util.ValidationError;

public class ImageExceptionResponse extends DefaultExceptionResponse<ValidationError> {

    public ImageExceptionResponse(String message, List<ValidationError> errors) {
        super(message, errors);
    }

}
