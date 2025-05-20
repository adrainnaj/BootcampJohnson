package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public FileManager() {
    }

    public static Dealership getDealership() {
        //D & B Used Cars|111 Old Benbrook Rd|817-555-5555
        //10112|1993|Ford|Explorer|SUV|Red|525123|995.00
        String filePath = "src/main/resources/inventory.csv";
        File file = new File(filePath);

        try {
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //dealership info
            String dealershipInfo = bufferedReader.readLine();
            String[] dealershipParts = dealershipInfo.split("\\|");

            String name = dealershipParts[0];
            String address = dealershipParts[1];
            String phone = dealershipParts[2];

            Dealership dealership = new Dealership(name, address, phone);

            String input;
            List<Vehicle> vehicleList = new ArrayList<>();

            while ((input = bufferedReader.readLine()) != null) {
                String[] row = input.split("\\|");

                int vin = Integer.parseInt(row[0]);
                int year = Integer.parseInt(row[1]);
                String make = row[2];
                String model = row[3];
                String vehicleType = row[4];
                String color = row[5];
                int odometer = Integer.parseInt(row[6]);
                double price = Double.parseDouble(row[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType,
                        color, odometer, price);
                vehicleList.add(vehicle);
            }
            bufferedReader.close();

            for (Vehicle vehicle : vehicleList) {
                dealership.addVehicle(vehicle);
            }

            return dealership;

        } catch (IOException ex) {
            System.out.println("Failed to load file");
            ex.printStackTrace();
            return null;
        }
    }

    public void saveDealership(Dealership dealership) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/inventory.csv");

            String header = dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n";
            fileWriter.write(header);

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                fileWriter.write(vehicle.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving inventory");
            e.printStackTrace();
        }
    }
}
