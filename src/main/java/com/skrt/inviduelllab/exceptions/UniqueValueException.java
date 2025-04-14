package com.skrt.inviduelllab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueValueException extends RuntimeException {

    public UniqueValueException(String object, String value) {
        super(object + ": {" + value + "} already exists and duplicates is not allowed"  );
    }
}
