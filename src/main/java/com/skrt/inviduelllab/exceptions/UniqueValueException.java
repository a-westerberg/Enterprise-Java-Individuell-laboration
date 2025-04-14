package com.skrt.inviduelllab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueValueException extends RuntimeException {
    private String object;
    private String value;
//TODO kolla med micke om det räcker med en httpsstatus som returneras eller om det ska medfölja en sträng/body (väntar svar)
    public UniqueValueException(String object, String value) {
        super(object + "  {" + value + "} already exists and duplicates is not allowed"  );
        this.object = object;
        this.value = value;
    }
}
