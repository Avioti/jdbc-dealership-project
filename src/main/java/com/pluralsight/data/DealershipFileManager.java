package com.pluralsight.data;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;

public class DealershipFileManager {
    public static String filePath = "src/main/resources/inventory.csv";

   public static Dealership getDealership(){


       try{
           BufferedReader reader = new BufferedReader(new FileReader(filePath));

           String titleLine = reader.readLine();
           String[] vehicleData = titleLine.split("\\|");

           String dealershipName = vehicleData[0];
           String dealershipAddress = vehicleData[1];
           String dealershipPhone = vehicleData[2];

           Dealership dealership = new Dealership(dealershipName, dealershipAddress, dealershipPhone);

           String input;

           while((input = reader.readLine()) != null){
               String[] vehicleInfo = input.split("\\|");
                if(!vehicleInfo[0].equals(dealershipName)){
                    Vehicle vehicle = getVehicle(vehicleInfo);
                    dealership.addVehicle(vehicle);
                }

           }
           reader.close();

           return dealership;

       }catch (Exception e){
              e.printStackTrace();
              return null;
       }



   }

    private static Vehicle getVehicle(String[] vehicleInfo) {
        int vin = Integer.parseInt(vehicleInfo[0]);
        int year = Integer.parseInt(vehicleInfo[1]);
        String make = vehicleInfo[2];
        String model = vehicleInfo[3];
        String vehicleType = vehicleInfo[4];
        String color = vehicleInfo[5];
        int odometer = Integer.parseInt(vehicleInfo[6]);
        double price = Double.parseDouble(vehicleInfo[7]);

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        return vehicle;
    }

    public static Dealership saveDealership(Dealership dealership){
        return dealership;
    }


}
