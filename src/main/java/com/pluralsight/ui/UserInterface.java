package com.pluralsight.ui;

import com.pluralsight.data.ContractDao;
import com.pluralsight.data.DealershipDao;
import com.pluralsight.data.VehicleDao;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


import static com.pluralsight.utility.Utility.ifNumber;

public class UserInterface {
    private Dealership dealership;
    private AdminUserInterface adminUserInterface;
    private boolean running = true;
    private int adminCreated;
    private VehicleDao vehicleDao;
    private ContractDao contractDao;
    private DealershipDao dealershipDao;
    private Vehicle vehicles;
    public static Scanner scanner = new Scanner(System.in);


    public UserInterface(VehicleDao vehicleDao, ContractDao contractDao, DealershipDao dealershipDao) {
        this.vehicleDao = vehicleDao;
        this.contractDao = contractDao;
        this.dealershipDao = dealershipDao;

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
        System.out.println("6 - Find Vehicles by type (Sedan, truck, SUV, Hatchback, etc.)");
        System.out.println("7 - List ALL Vehicles");
        System.out.println("8 - Add a Vehicle");
        System.out.println("9 - Remove a Vehicle");
        System.out.println("10 - Sell/Lease a Vehicle");

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

    }


    public void processGetByPriceRequest() {

        System.out.print("Min: ");
        double min = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Max: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        System.out.println(vehicleDao.getByPriceRange(min, max));


    }

    public void processGetByMakeModelRequest() {

        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();


        System.out.println(vehicleDao.getByMakeModel(make, model));

    }

    public void processGetByYearRequest() {

        System.out.print("Min: ");
        int min = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Max: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.println(vehicleDao.getByYearRange(min, max));

    }

    public void processGetByColorRequest() {
        System.out.print("Color: ");
        String color = scanner.nextLine();


        System.out.println(vehicleDao.getByColor(color));
    }

    public void processGetByMileageRequest() {

        System.out.print("Min: ");
        int min = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Max: ");
        int max = scanner.nextInt();
        scanner.nextLine();


        System.out.println(vehicleDao.getByMileageRange(min, max));

    }

    public void processGetByVehicleTypeRequest() {

        System.out.print("Vehicle Type: ");
        String type = scanner.nextLine();


        System.out.println(vehicleDao.getByType(type));

    }

    public void processGetAllVehiclesRequest() {

        System.out.println(vehicleDao.getAll() + "\n");

    }

    public List<Vehicle> processVehicleByVin(String vin) {

        return vehicleDao.getByVin(vin);
    }

    public void processAddVehicleRequest() {

        System.out.println("Enter Vehicle Details");

        System.out.print("Vin: ");
        String vin = scanner.nextLine();
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

        vehicleDao.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));


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


        for (Vehicle car : vehicleDao.getAll()) {


            boolean matches = true;

            try {
                if (!vin.isBlank() && !car.getVin().equalsIgnoreCase(vin)) {
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
                vehicleDao.removeVehicle(car.getVin());
                System.out.println("Vehicle removed.");
            }


        }

    }


    public void processSellLease() {
        System.out.println("Hello Thank you for inquiring before we move forward please answer the following questions.");
        LocalDate date = LocalDate.now();

        System.out.print("What is your name: ");
        String name = scanner.nextLine();

        System.out.print("Your Email: ");
        String email = scanner.nextLine();

        System.out.println("What vehicle are you interested in?");
        System.out.print("Enter Vin #: ");
        String vin = scanner.nextLine();
        List<Vehicle> vehicle = processVehicleByVin(vin);
        double price = vehicle.get(0).getPrice();

        System.out.println("Will you be Purchasing or Leasing this Vehicle?");
        System.out.println("1 - Buying");
        System.out.println("2 - Leasing");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1 -> {

                System.out.println("Will you be Financing? (Y/N)");

                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    contractDao.saveSalesContract(new SalesContract(date, name, email, vin, price, true));
                } else {
                    contractDao.saveSalesContract(new SalesContract(date, name, email, vin, price, false));
                }
                System.out.println("Sales contract saved!");
                vehicleDao.removeVehicle(vehicle);
            }
            case 2 -> {
                System.out.println("Will you be leasing to own? (Y/N)");
                boolean leaseToOwn;
                leaseToOwn = scanner.nextLine().equalsIgnoreCase("y") || scanner.nextLine().equalsIgnoreCase("yes");
                int currentDate = LocalDateTime.now().getYear();
                int age = currentDate - vehicle.get(0).getYear();
                if (age > 3) {
                    System.out.println("Sorry cannot lease this Vehicle");
                    break;
                }
                contractDao.saveLeaseContract(new LeaseContract(date, name, email, vin, price, leaseToOwn));
                System.out.println("Lease contract saved!");
                vehicleDao.removeVehicle(vehicle);

            }
        }

    }

    public void adminSetup() {

    }

    public void adminCommand() {
        if (adminCreated >= 1) {

        }

    }

}


