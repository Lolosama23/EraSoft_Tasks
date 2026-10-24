package com.lo2ay.util;

import com.lo2ay.exception.InvalidInputException;

import java.time.Year;

public class InputValidator {
    private InputValidator() {
        // static helpers only
    }

    public static void validatePlate(String plate) {
        if (plate == null || plate.trim().isEmpty()
                || !plate.trim().toUpperCase().matches("[A-Z0-9-]{3,10}")) {
            throw new InvalidInputException("Plate number must be 3-10 alphanumeric characters");
        }
    }

    public static void validateOwnerName(String name) {
        // spaces are allowed so full names like "Ahmed Ali" work
        if (name == null || name.trim().length() < 3 || !name.trim().matches("[A-Za-z ]+")) {
            throw new InvalidInputException("Owner name must contain only letters");
        }
    }

    public static void validateType(String type) {
        if (type == null || !(type.equalsIgnoreCase("Car")
                || type.equalsIgnoreCase("Truck")
                || type.equalsIgnoreCase("Motorcycle"))) {
            throw new InvalidInputException("Invalid type. Use: Car, Truck, Motorcycle");
        }
    }

    public static void validateYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1990 || year > currentYear) {
            throw new InvalidInputException("Year must be between 1990 and " + currentYear);
        }
    }
}
