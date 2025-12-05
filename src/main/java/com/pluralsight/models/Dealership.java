package com.pluralsight.models;

import com.pluralsight.data.DealershipFileManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name,address,phone;
    private int ID;
    private ArrayList<Vehicle> inventory;
    private ArrayList<Vehicle> matches;

    public Dealership(int ID,String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
        inventory = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public List<Vehicle> getVehiclesByPrice(double min, double max){

        matches = new ArrayList<>();


        for(Vehicle car : inventory){
            double carPrice = car.getPrice();

            if (carPrice <= max && carPrice >= min) {
                matches.add(car);
            }

        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
       matches = new ArrayList<>();


        for(Vehicle car : inventory){
            String carMake = car.getMake().trim().replaceAll("\\s", "");

            String carModel = car.getModel().trim().replaceAll("\\s", "");

            if (carMake.equalsIgnoreCase(make) && carModel.equalsIgnoreCase(model)) {
                matches.add(car);
            }

        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max){

        matches = new ArrayList<>();


        for(Vehicle car : inventory){
            int carYear = car.getYear();

            if (carYear <= max && carYear >= min) {
                matches.add(car);
            }

        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color){
        matches = new ArrayList<>();


        for(Vehicle car : inventory){
            String carColor = car.getColor().trim().replaceAll("\\s", "");



            if (carColor.equalsIgnoreCase(color)) {
                matches.add(car);
            }

        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max){
        matches = new ArrayList<>();


        for(Vehicle car : inventory){
            int carMiles = car.getOdometer();

            if (carMiles <= max && carMiles >= min) {
                matches.add(car);
            }

        }
        return matches;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType){

        matches = new ArrayList<>();


        for(Vehicle car : inventory){
            String carVehicleType = car.getVehicleType().trim().replaceAll("\\s", "");

            if (carVehicleType.equalsIgnoreCase(vehicleType)) {
                matches.add(car);
            }

        }
        return matches;
    }

    public ArrayList<Vehicle> getAllVehicle(){

        return inventory;
    }




    public void removeVehicle(Vehicle vehicle){


        inventory.remove(vehicle);
        DealershipFileManager.saveDealership(DealershipFileManager.getDealership());
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(DealershipFileManager.filePath, false));


                String dealershipInfo = String.format("%s|%s|%s",this.name,this.address,this.phone);
                bufWriter.write(dealershipInfo);


            for (Vehicle car : inventory) {
                bufWriter.newLine();
                String format = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f", car.getVin(), car.getYear(), car.getMake(), car.getModel(), car.getVehicleType(),
                        car.getColor(),car.getOdometer(),car.getPrice());
                bufWriter.write(format);
            }

            bufWriter.close();

            System.out.println("Updated");
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s",this.name,this.address,this.phone);
    }
}
