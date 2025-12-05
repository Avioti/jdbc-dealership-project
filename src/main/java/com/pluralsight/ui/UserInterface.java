package com.pluralsight.ui;

import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;
import com.pluralsight.data.ContractFileManager;
import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;
import com.pluralsight.data.DealershipFileManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.pluralsight.Program.scanner;
import static com.pluralsight.utility.Utility.ifNumber;

public class UserInterface {
    private Dealership dealership;
    private ContractFileManager contractFileManager;
    private AdminUserInterface adminUserInterface;
    private boolean running = true;
    private int adminCreated;


    public UserInterface() {

    }

    public void display() {
        init();


        while (running) {

            displayMenu();

            userInput();


        }

    }

    public void displayMenu() {
        System.out.println("1 - Find Vehicles within a price range");
        System.out.println("2 - Find Vehicles by make / model");
        System.out.println("3 - Find Vehicles by year range");
        System.out.println("4 - Find Vehicles by color");
        System.out.println("5 - Find Vehicles by mileage range");
        System.out.println("6 - Find Vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL Vehicles");
        System.out.println("8 - Add a Vehicle");
        System.out.println("9 - Remove a Vehicle");
        System.out.println("10 - Sell/Lease a Vehicle");
        System.out.println("11 - Admin Menu");
        System.out.println("99 - Quit");
    }

    public void userInput() {

        String option = scanner.nextLine().trim();

        if (ifNumber(option)) {
            int choice = Integer.parseInt(option);
            switch (choice) {
                case 1 -> processGetByPriceRequest();

                case 2 -> processGetByMakeModelRequest();

                case 3 -> processGetByYearRequest();

                case 4 -> processGetByColorRequest();

                case 5 -> processGetByMileageRequest();

                case 6 -> processGetByVehicleTypeRequest();

                case 7 -> processGetAllVehiclesRequest();

                case 8 -> processAddVehicleRequest();

                case 9 -> processRemoveVehicleRequest();

                case 10 -> processSellLease();

                case 11 -> adminSetup();

                case 99 -> running = false;

                default -> System.out.println("Enter valid option");

            }
        } else {
            System.out.println();
            System.out.println("Enter valid option");
        }
    }

    private void init() {
        dealership = DealershipFileManager.getDealership();
        contractFileManager = new ContractFileManager();

        System.out.println(DealershipFileManager.getDealership());
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle car : vehicles) {

            System.out.println(car);

        }

    }

    public void processGetByPriceRequest() {

        System.out.print("Min: ");
        double min = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Max: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        System.out.println(dealership.getVehiclesByPrice(min, max));


    }

    public void processGetByMakeModelRequest() {

        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();


        System.out.println(dealership.getVehiclesByMakeModel(make, model));

    }

    public void processGetByYearRequest() {

        System.out.print("Min: ");
        int min = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Max: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.println(dealership.getVehiclesByYear(min, max));

    }

    public void processGetByColorRequest() {
        System.out.print("Color: ");
        String color = scanner.nextLine();


        System.out.println(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {

        System.out.print("Min: ");
        int min = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Max: ");
        int max = scanner.nextInt();
        scanner.nextLine();


        System.out.println(dealership.getVehiclesByMileage(min, max));

    }

    public void processGetByVehicleTypeRequest() {

        System.out.print("Vehicle Type: ");
        String type = scanner.nextLine();


        System.out.println(dealership.getVehiclesByType(type));

    }

    public void processGetAllVehiclesRequest() {

        ArrayList<Vehicle> vehicleList = dealership.getAllVehicle();


        displayVehicles(vehicleList);

    }

    public Vehicle processVehicleByVin(int vin) {
        for (Vehicle vehicle : dealership.getAllVehicle()) {
            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        return null;
    }

    public void processAddVehicleRequest() {

        System.out.println("Enter Vehicle Details");

        System.out.print("Vin: ");
        int vin = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        dealership.addVehicle(vin, year, make, model, vehicleType, color, odometer, price);

    }

    public void processRemoveVehicleRequest() {

        System.out.println("Enter Vehicle Details to Remove (Press enter to leave Blank)");

        System.out.print("Vin: ");
        String vin = scanner.nextLine().trim().replaceAll("\\s", "");

        System.out.print("Year: ");
        String year = scanner.nextLine().trim().replaceAll("\\s", "");

        System.out.print("Make: ");
        String make = scanner.nextLine().trim().replaceAll("\\s", "");

        System.out.print("Model: ");
        String model = scanner.nextLine().trim().replaceAll("\\s", "");

        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.nextLine().trim().replaceAll("\\s", "");

        System.out.print("Color: ");
        String color = scanner.nextLine().trim().replaceAll("\\s", "");

        System.out.print("Odometer: ");
        String odometer = scanner.nextLine().trim().replaceAll("\\s", "");


        System.out.print("Price: ");
        String price = scanner.nextLine().trim().replaceAll("\\s", "");


        for (Vehicle car : dealership.getAllVehicle()) {


            boolean matches = true;

            try {
                if (!vin.isBlank() && car.getVin() == Integer.parseInt(vin)) {
                    matches = false;
                }
                if (!year.isBlank() && car.getYear() == Integer.parseInt(year)) {
                    matches = false;
                }
                if (!make.isBlank() && !car.getMake().equalsIgnoreCase(make)) {
                    matches = false;
                }
                if (!model.isBlank() && !car.getModel().equalsIgnoreCase(model)) {
                    matches = false;
                }
                if (!vehicleType.isBlank() && !car.getVehicleType().equalsIgnoreCase(vehicleType)) {
                    matches = false;
                }
                if (!color.isBlank() && !car.getColor().equalsIgnoreCase(color)) {
                    matches = false;
                }
                if (!odometer.isBlank() && car.getOdometer() != Integer.parseInt(odometer)) {
                    matches = false;
                }
                if (!price.isBlank() && car.getPrice() != Double.parseDouble(price)) {
                    matches = false;
                }


            } catch (Exception e) {

                e.printStackTrace();
            }


            if (matches) {
                dealership.removeVehicle(car);
                System.out.println("Vehicle removed.");
            }


        }

    }



    public void processSellLease() {
        System.out.println("Hello Thank you for inquiring before we move forward please answer the following questions.");
        System.out.print("Today's Date: ");
        String date = scanner.nextLine();

        System.out.print("What is your name: ");
        String name = scanner.nextLine();

        System.out.print("Your Email: ");
        String email = scanner.nextLine();

        System.out.println("What vehicle are you interested in?");
        System.out.print("Enter Vin #: ");
        int vin = scanner.nextInt();
        scanner.nextLine();
        Vehicle vehicle = processVehicleByVin(vin);

        System.out.println("Will you be Purchasing or Leasing this Vehicle?");
        System.out.println("1 - Buying");
        System.out.println("2 - Leasing");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1 -> {

                System.out.println("Will you be Financing? (Y/N)");
                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    contractFileManager.saveContract(new SalesContract(1,date, name, email, vehicle, true));
                } else {
                    contractFileManager.saveContract(new SalesContract(1,date, name, email, vehicle, false));
                }
                dealership.removeVehicle(vehicle);
            }
            case 2 -> {
                int currentDate = LocalDateTime.now().getYear();
                int age = currentDate - vehicle.getYear();
                if (age > 3) {
                    System.out.println("Sorry cannot lease this Vehicle");
                    break;
                }
                contractFileManager.saveContract(new LeaseContract(1,date, name, email, vehicle));
                dealership.removeVehicle(vehicle);

            }
        }

    }

    public void adminSetup(){
        if(adminCreated == 0){
            System.out.println("Admin Setup");
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            adminUserInterface = new AdminUserInterface(password,contractFileManager);
            adminCreated += 1;
            adminUserInterface.display();
        }else{
            System.out.println("Log in");
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            adminUserInterface.authenticate(password);
            adminUserInterface.display();
        }
    }

    public void adminCommand(){
        if(adminCreated >= 1){

        }

    }

}


