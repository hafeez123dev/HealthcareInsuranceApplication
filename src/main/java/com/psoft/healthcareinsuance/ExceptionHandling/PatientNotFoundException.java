package com.psoft.healthcareinsuance.ExceptionHandling;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
