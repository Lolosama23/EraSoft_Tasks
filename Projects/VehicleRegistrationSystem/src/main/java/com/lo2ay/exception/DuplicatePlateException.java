package com.lo2ay.exception;

public class DuplicatePlateException extends RuntimeException {
    public DuplicatePlateException(String plateNumber) {
        super("Plate number already registered: " + plateNumber);
    }
}
