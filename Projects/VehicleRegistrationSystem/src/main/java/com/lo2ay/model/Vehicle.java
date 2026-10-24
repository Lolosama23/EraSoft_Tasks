package com.lo2ay.model;

import java.util.Objects;

public abstract class Vehicle {

    private final String plateNumber;
    private String ownerName;
    private final String vehicleType;
    private final int registrationYear;
    private String status;

    public Vehicle(String plateNumber, String ownerName, String vehicleType,
                   int registrationYear, String status) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.registrationYear = registrationYear;
        this.status = status;
    }

    public abstract String getRegistrationLabel();

    public String getPlateNumber() { return plateNumber; }
    public String getOwnerName() { return ownerName; }
    public String getVehicleType() { return vehicleType; }
    public int getRegistrationYear() { return registrationYear; }
    public String getStatus() { return status; }

    // plate number never changes, so no setter for it
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return plateNumber.equalsIgnoreCase(vehicle.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber.toLowerCase());
    }

    @Override
    public String toString() {
        return "[" + plateNumber + "] | " + vehicleType + " | Owner: " + ownerName
                + " | Year: " + registrationYear + " | Status: " + status;
    }
}
