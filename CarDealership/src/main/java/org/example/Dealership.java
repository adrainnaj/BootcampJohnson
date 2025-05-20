package org.example;

import java.util.ArrayList;
import java.util.List;

 public class Dealership {
    private String name;
    private String address;
    private String phone;
    public List<Vehicle> inventory;

     public Dealership(String name, String address, String phone, List<Vehicle> inventory) {
         this.name = name;
         this.address = address;
         this.phone = phone;
         this.inventory = inventory;
     }

     public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public String getPhone() {
         return phone;
     }

     public void setPhone(String phone) {
         this.phone = phone;
     }

     public List<Vehicle> getInventory() {
         return inventory;
     }

     public void setInventory(List<Vehicle> inventory) {
         this.inventory = inventory;
     }

     public List<Vehicle> getVehicleByPrice(double min, double max) {
        List<Vehicle> result = new ArrayList<>();

        for(Vehicle vehicle : inventory){
            if(vehicle.getPrice() >= min && vehicle.getPrice() <= max){
                result.add(vehicle);
            }
        }
        return result;
    }
    public List<Vehicle> getVehicleByMakeModel(String make,String model){
        List<Vehicle> result = new ArrayList<>();

        for(Vehicle vehicle : inventory){
            if(vehicle.getMake().equalsIgnoreCase(make)&& vehicle.getModel().equalsIgnoreCase(model)){
                result.add(vehicle);
            }
        }
        return result;
    }
    public List<Vehicle> getVehicleByYear(int min, int max){
        List<Vehicle> result = new ArrayList<>();

        for(Vehicle vehicle: inventory){
            if(vehicle.getYear() >= min && vehicle.getYear() <= max){
                result.add(vehicle);
            }
        }
        return result;
    }
    public List<Vehicle> getVehicleByColor(String color){
        List<Vehicle>result = new ArrayList<>();

        for(Vehicle vehicle : inventory){
            if(vehicle.getColor().equalsIgnoreCase(color)){
                result.add(vehicle);
            }
        }
        return result;
    }
    public List<Vehicle> getByMileage(double min, double max){
        List<Vehicle> result = new ArrayList<>();

        for(Vehicle vehicle : inventory){
            if(vehicle.getOdometer() >= min && vehicle.getOdometer() <= max){
                result.add(vehicle);
            }
        }
        return result;
    }
    public List<Vehicle> getVehicleByType(String vehicleType){
        List<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory){
            if(vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                results.add(vehicle);
            }
        }
        return results;
    }
    public List<Vehicle> getAllVehicles(){
        return new ArrayList<> (inventory);
    }
    public void addVehicle(Vehicle vehicle){
         if(vehicle != null)
        inventory.add(vehicle);

    }
    public void removeVehicle(Vehicle vehicle){
         if(vehicle != null)
        inventory.remove(vehicle);
    }
}
