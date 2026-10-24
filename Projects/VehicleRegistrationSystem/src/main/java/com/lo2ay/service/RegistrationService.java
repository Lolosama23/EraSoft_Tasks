package com.lo2ay.service;

import com.lo2ay.exception.DuplicatePlateException;
import com.lo2ay.exception.VehicleNotFoundException;
import com.lo2ay.model.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class RegistrationService {

    private List<Vehicle> vehicleList = new ArrayList<>();          // keeps insertion order
    private Map<String, Vehicle> plateIndex = new HashMap<>();      // fast lookup by plate
    private Set<String> registeredPlates = new HashSet<>();         // fast duplicate check

    public void registerVehicle(Vehicle v) {
        String plate = v.getPlateNumber().toUpperCase();
        if (registeredPlates.contains(plate)) {
            throw new DuplicatePlateException(plate);
        }
        vehicleList.add(v);
        plateIndex.put(plate, v);
        registeredPlates.add(plate);
    }

    public Vehicle findByPlate(String plate) {
        String key = plate.trim().toUpperCase();
        Vehicle v = plateIndex.get(key);
        if (v == null) {
            throw new VehicleNotFoundException(key);
        }
        return v;
    }

    public void deleteVehicle(String plate) {
        Vehicle v = findByPlate(plate); // throws if missing
        String key = v.getPlateNumber().toUpperCase();
        vehicleList.remove(v);
        plateIndex.remove(key);
        registeredPlates.remove(key);
    }

    public void updateOwner(String plate, String newOwner) {
        Vehicle v = findByPlate(plate);
        v.setOwnerName(newOwner);
    }

    public List<Vehicle> getAllVehicles() {
        return Collections.unmodifiableList(vehicleList);
    }

    // ---------- Streams (Task 7) ----------

    public List<Vehicle> filterByType(String type) {
        return vehicleList.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByOwner(String ownerName) {
        return vehicleList.stream()
                .filter(v -> v.getOwnerName().toLowerCase().contains(ownerName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getExpiredRegistrations(int currentYear) {
        return vehicleList.stream()
                .filter(v -> (currentYear - v.getRegistrationYear()) > 5)
                .sorted(Comparator.comparingInt(Vehicle::getRegistrationYear))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getSortedByYear(boolean ascending) {
        Comparator<Vehicle> comp = Comparator.comparingInt(Vehicle::getRegistrationYear);
        if (!ascending) {
            comp = comp.reversed();
        }
        return vehicleList.stream().sorted(comp).collect(Collectors.toList());
    }

    // ---------- Statistics (Task 8) ----------

    public void printStatistics() {
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles registered yet, nothing to report.");
            return;
        }

        IntSummaryStatistics stats = vehicleList.stream()
                .mapToInt(Vehicle::getRegistrationYear)
                .summaryStatistics();

        Map<String, Long> byType = vehicleList.stream()
                .collect(Collectors.groupingBy(Vehicle::getVehicleType, Collectors.counting()));

        Map<Boolean, Long> byStatus = vehicleList.stream()
                .collect(Collectors.partitioningBy(
                        v -> v.getStatus().equals("ACTIVE"), Collectors.counting()));

        System.out.println("========== REGISTRATION STATISTICS ==========");
        System.out.printf("Total Vehicles : %d%n", stats.getCount());
        System.out.printf("Average Year   : %.0f%n", stats.getAverage());
        System.out.printf("Newest Vehicle : %d%n", stats.getMax());
        System.out.printf("Oldest Vehicle : %d%n", stats.getMin());
        System.out.println("----------------------------------------------");
        System.out.println("Vehicles by Type:");
        byType.forEach((type, count) -> System.out.printf("  %-11s : %d%n", type, count));
        System.out.println("----------------------------------------------");
        System.out.printf("ACTIVE vehicles : %d%n", byStatus.get(true));
        System.out.printf("EXPIRED vehicles: %d%n", byStatus.get(false));
        System.out.println("==============================================");
    }
}
