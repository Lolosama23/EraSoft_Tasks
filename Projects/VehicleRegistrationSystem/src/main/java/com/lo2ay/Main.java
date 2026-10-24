package com.lo2ay;

import com.lo2ay.exception.DuplicatePlateException;
import com.lo2ay.exception.InvalidInputException;
import com.lo2ay.exception.VehicleNotFoundException;
import com.lo2ay.model.Car;
import com.lo2ay.model.Motorcycle;
import com.lo2ay.model.Truck;
import com.lo2ay.model.Vehicle;
import com.lo2ay.service.RegistrationService;
import com.lo2ay.util.InputValidator;

import java.time.Year;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistrationService service = new RegistrationService();

        // seed data
        service.registerVehicle(new Car("CAR-001", "Ahmed Ali", 2019, "EXPIRED", 4));
        service.registerVehicle(new Car("CAR-002", "Sara Kamel", 2023, "ACTIVE", 2));
        service.registerVehicle(new Truck("TRK-001", "Mohamed Said", 2021, "ACTIVE", 10.0));
        service.registerVehicle(new Truck("TRK-002", "Laila Nour", 2017, "EXPIRED", 5.5));
        service.registerVehicle(new Motorcycle("MOT-001", "Omar Fathi", 2022, "ACTIVE", "Sport"));
        service.registerVehicle(new Motorcycle("MOT-002", "Nadia Hamed", 2024, "ACTIVE", "Cruiser"));

        while (true) {
            printMenu();
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // clear the newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // throw away the bad input
                continue;
            }

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                switch (choice) {
                    case 1:
                        registerNew(sc, service);
                        break;
                    case 2:
                        System.out.print("Plate number: ");
                        System.out.println(service.findByPlate(sc.nextLine()));
                        break;
                    case 3:
                        System.out.print("Plate number: ");
                        String plate = sc.nextLine();
                        System.out.print("New owner name: ");
                        String newOwner = sc.nextLine();
                        InputValidator.validateOwnerName(newOwner);
                        service.updateOwner(plate, newOwner.trim());
                        System.out.println("Owner updated.");
                        break;
                    case 4:
                        System.out.print("Plate number to delete: ");
                        service.deleteVehicle(sc.nextLine());
                        System.out.println("Vehicle deleted.");
                        break;
                    case 5:
                        printList(service.getAllVehicles());
                        break;
                    case 6:
                        System.out.print("Type (Car, Truck, Motorcycle): ");
                        String type = sc.nextLine();
                        InputValidator.validateType(type);
                        printList(service.filterByType(type));
                        break;
                    case 7:
                        System.out.print("Owner name: ");
                        printList(service.getVehiclesByOwner(sc.nextLine()));
                        break;
                    case 8:
                        printList(service.getExpiredRegistrations(Year.now().getValue()));
                        break;
                    case 9:
                        service.printStatistics();
                        break;
                    default:
                        System.out.println("Invalid choice. Please pick a number from the menu.");
                }
            } catch (DuplicatePlateException | VehicleNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Input Error: " + e.getMessage());
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("  VEHICLE REGISTRATION SYSTEM v1.0");
        System.out.println("========================================");
        System.out.println("1. Register New Vehicle");
        System.out.println("2. Search Vehicle by Plate");
        System.out.println("3. Update Owner Name");
        System.out.println("4. Delete Vehicle");
        System.out.println("5. List All Vehicles");
        System.out.println("6. Filter by Vehicle Type");
        System.out.println("7. Show Owner History");
        System.out.println("8. Show Expired Registrations");
        System.out.println("9. Statistics Report");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    private static void registerNew(Scanner sc, RegistrationService service) {
        System.out.print("Plate number: ");
        String plate = sc.nextLine().trim().toUpperCase();
        InputValidator.validatePlate(plate);

        System.out.print("Owner name: ");
        String owner = sc.nextLine().trim();
        InputValidator.validateOwnerName(owner);

        System.out.print("Type (Car, Truck, Motorcycle): ");
        String type = sc.nextLine().trim();
        InputValidator.validateType(type);

        System.out.print("Registration year: ");
        int year;
        try {
            year = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Year must be a number");
        }
        InputValidator.validateYear(year);

        Vehicle v;
        try {
            if (type.equalsIgnoreCase("Car")) {
                System.out.print("Number of doors: ");
                v = new Car(plate, owner, year, "ACTIVE", Integer.parseInt(sc.nextLine().trim()));
            } else if (type.equalsIgnoreCase("Truck")) {
                System.out.print("Cargo capacity (tons): ");
                v = new Truck(plate, owner, year, "ACTIVE", Double.parseDouble(sc.nextLine().trim()));
            } else {
                System.out.print("Engine type (Sport, Cruiser, Off-Road): ");
                v = new Motorcycle(plate, owner, year, "ACTIVE", sc.nextLine().trim());
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter a valid number");
        }

        service.registerVehicle(v);
        System.out.println("Registered: " + v);
    }

    private static void printList(List<Vehicle> list) {
        if (list.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle v : list) {
            System.out.println(v);
        }
    }
}
