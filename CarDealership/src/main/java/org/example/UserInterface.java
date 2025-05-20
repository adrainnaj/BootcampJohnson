package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class UserInterface {
    private Dealership dealership;
    private FileManager fileManager;

    private void init() {
        this.fileManager = new FileManager();
        this.dealership = fileManager.getDealership();
    }

    public void display() {
        init();
        Scanner scanner = new Scanner(System.in);
        boolean inDisplay = true;

        while (inDisplay) {
            System.out.println("\n Dealership Menu\n");
            System.out.println("1)Search by Price");
            System.out.println("2)Search Make/Model");
            System.out.println("3)Search Year");
            System.out.println("4)Search Color");
            System.out.println("5)Search by Mileage");
            System.out.println("6)Search by Vehicle Type");
            System.out.println("7)Search All Vehicles");
            System.out.println("8)Add New Vehicle");
            System.out.println("9)Remove Vehicle");
            System.out.println("10)Display Vehicle");
            System.out.println("11)Create Sale Contact");
            System.out.println("12)Create Lease Contract");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    processGetByPrice(scanner);
                    break;
                case 2:
                    processGetByMakeModel(scanner);
                    break;
                case 3:
                    processGetByYear(scanner);
                    break;
                case 4:
                    processGetByColor(scanner);
                    break;
                case 5:
                    processGetByMileage(scanner);
                    break;
                case 6:
                    processGetByVehicleType(scanner);
                    break;
                case 7:
                    processGetAllVehicles(scanner);
                    break;
                case 8:
                    processAddVehicle(scanner);
                    break;
                case 9:
                    processRemoveVehicle(scanner);
                    break;
                case 10:
                    displayVehicle(dealership.getAllVehicles());
                    break;
                case 11:
                   // processSaleContract(scanner);
                    break;
                case 12:
                   // processLeaseContract(scanner);
                    break;
            }
        }
    }

    public void processGetByPrice(Scanner scanner) {
        System.out.println("\n Search Price");
        System.out.println("What is the minimum price you're looking to pay?");
        double minPrice = scanner.nextDouble();
        System.out.println("What is the maximum price you're looking to pay?");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();
        List<Vehicle> results = dealership.getVehicleByPrice(minPrice, maxPrice);
        displayVehicle(results);
    }

    private void processGetByMakeModel(Scanner scanner) {
        scanner.nextLine();

        System.out.println("\nSearch by Make/Model");
        System.out.println("Enter Vehicle Make");
        String vehicleMake = scanner.nextLine();

        System.out.println(("Enter Vehicle Model"));
        String vehicleModel = scanner.nextLine();

        List<Vehicle> results = dealership.getVehicleByMakeModel(vehicleMake, vehicleModel);
        displayVehicle(results);
    }

    private void processGetByYear(Scanner scanner) {
        System.out.println("\nSearch by Year");
        System.out.println("Enter Year");
        int year = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehicleByYear(year, year);
        displayVehicle(vehicles);
    }

    private void processGetByColor(Scanner scanner) {
        System.out.println("\nSearch by Color");

        scanner.nextLine();
        System.out.println("Enter Color: ");
        String color = scanner.nextLine();

        List<Vehicle> results = dealership.getVehicleByColor(color);
        displayVehicle(results);
    }

    private void processGetByMileage(Scanner scanner) {
        System.out.println("\nSearch by Mileage");
        System.out.println("What is the minimum mileage you would like ?");
        double minMileage = scanner.nextDouble();
        System.out.println("What is the maximum mileage you would like?");
        double maxMileage = scanner.nextDouble();
        scanner.nextLine();

        List<Vehicle> result = dealership.getByMileage(minMileage, maxMileage);
        displayVehicle(result);
    }

    private void processGetByVehicleType(Scanner scanner) {
        System.out.println("Search by Vehicle Type");
        scanner.nextLine();
        System.out.println("Enter Vehicle Type");
        String vehicleType = scanner.nextLine();

        List<Vehicle> results = dealership.getVehicleByType(vehicleType);
        displayVehicle(results);
    }

    private void processGetAllVehicles(Scanner scanner) {
        System.out.println("Show all Vehicles");
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicle(vehicles);
    }

    private void processAddVehicle(Scanner scanner) {
        System.out.println("Add New Vehicle");

        System.out.println("Enter VIN");
        int vin = scanner.nextInt();

        System.out.println("Add year of Vehicle");
        int year = scanner.nextInt();

        System.out.println("Add Vehicle Make");
        scanner.nextLine();
        String make = scanner.nextLine();
        System.out.println("Add Vehicle Model");
        String model = scanner.nextLine();

        System.out.println("Add Vehicle Type");
        String vehicleType = scanner.nextLine();

        System.out.println("Add Color of Vehicle");
        String color = scanner.nextLine();

        System.out.println("Add Vehicle Mileage");
        int odometer = scanner.nextInt();

        System.out.println("Add Price");
        double price = scanner.nextDouble();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);

        fileManager.saveDealership(dealership);
        System.out.println("New vehicle added !");
    }

    private void processRemoveVehicle(Scanner scanner) {
        System.out.println("Remove vehicle");
        System.out.println("Enter VIN");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle toRemove = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                toRemove = vehicle;
                break;
            }
        }
        if(toRemove != null){
            dealership.removeVehicle(toRemove);
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle removed: " + toRemove);
        }
        else{
            System.out.println("No vehicle found with that vin");
        }
    }

    private void displayVehicle(List<Vehicle> vehicles) {
        if (vehicles == null) {
            System.out.println("No vehicles found");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }
}
