package com.skrt.inviduelllab.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String object;
    private String field;
    private Object value;

    public ResourceNotFoundException(String object, String field, Object value) {
        super(object + " with " + field + ": " + value + " not found");
        this.object = object;
        this.field = field;
        this.value = value;
    }
}

