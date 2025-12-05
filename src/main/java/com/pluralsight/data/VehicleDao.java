package com.pluralsight.data;

import com.pluralsight.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private final DataManager dataManager;

    public VehicleDao(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    public void addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (vin, year, make, model, vehicle_type, color, odometer, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, vehicle.getVin());
                statement.setInt(2, vehicle.getYear());
                statement.setString(3, vehicle.getMake());
                statement.setString(4, vehicle.getModel());
                statement.setString(5, vehicle.getVehicleType());
                statement.setString(6, vehicle.getColor());
                statement.setInt(7, vehicle.getOdometer());
                statement.setDouble(8, vehicle.getPrice());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ;
        }
    }

    public void removeVehicle(String vin) {

        String deleteInventory = "DELETE FROM inventory WHERE vin = ?";
        String markAsSold = "UPDATE vehicles SET sold = TRUE WHERE vin = ?";

        try {
            Connection connection = dataManager.getConnection();


            try (PreparedStatement statement = connection.prepareStatement(deleteInventory)) {
                statement.setString(1, vin);
                statement.executeUpdate();
            }


            try (PreparedStatement statement = connection.prepareStatement(markAsSold)) {
                statement.setString(1, vin);
                statement.executeUpdate();
            }

            System.out.println("Vehicle removed from inventory and marked as sold.");
        } catch (SQLException e) {
            System.err.println("Error removing vehicle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeVehicle(List<Vehicle> vehicles) {
        vehicles.forEach(vehicle -> removeVehicle(vehicle.getVin()));
    }


    public void deleteVehicle(String vin) {
        String deleteSalesContracts = "DELETE FROM sales_contracts WHERE vin = ?";
        String deleteLeaseContracts = "DELETE FROM lease_contracts WHERE vin = ?";
        String deleteInventory = "DELETE FROM inventory WHERE vin = ?";
        String deleteVehicle = "DELETE FROM vehicles WHERE vin = ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(deleteSalesContracts)) {
                statement.setString(1, vin);
                statement.executeUpdate();
            }

            try (PreparedStatement statement = connection.prepareStatement(deleteLeaseContracts)) {
                statement.setString(1, vin);
                statement.executeUpdate();
            }

            try (PreparedStatement statement = connection.prepareStatement(deleteInventory)) {
                statement.setString(1, vin);
                statement.executeUpdate();
            }

            try (PreparedStatement statement = connection.prepareStatement(deleteVehicle)) {
                statement.setString(1, vin);
                statement.executeUpdate();
            }

            System.out.println("Vehicle completely deleted from database.");
        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet results = statement.executeQuery()) {

                    while (results.next()) {
                        String vin = results.getString("vin");
                        int year = results.getInt("year");
                        String make = results.getString("make");
                        String model = results.getString("model");
                        String vehicleType = results.getString("vehicle_type");
                        String color = results.getString("color");
                        int odometer = results.getInt("odometer");
                        double price = results.getDouble("price");

                        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        vehicles.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all vehicles: " + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }


    public List<Vehicle> getByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicle = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE price >= ? AND price <= ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, minPrice);
                statement.setDouble(2, maxPrice);

                try (ResultSet results = statement.executeQuery()) {

                    while (results.next()) {
                        String vin = results.getString("vin");
                        int year = results.getInt("year");
                        String make = results.getString("make");
                        String model = results.getString("model");
                        String vehicleType = results.getString("vehicle_type");
                        String color = results.getString("color");
                        int odometer = results.getInt("odometer");
                        double price = results.getDouble("price");

                        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        vehicle.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles by price range: " + e.getMessage());
            e.printStackTrace();
        }
        return vehicle;

    }


    public List<Vehicle> getByMakeModel(String make, String model) {
        List<Vehicle> vehicle = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, make);
                statement.setString(2, model);

                try (ResultSet results = statement.executeQuery()) {

                    while (results.next()) {
                        String vin = results.getString("vin");
                        int year = results.getInt("year");
                        String vehicleType = results.getString("vehicle_type");
                        String color = results.getString("color");
                        int odometer = results.getInt("odometer");
                        double price = results.getDouble("price");

                        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        vehicle.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles by make and model: " + e.getMessage());
            e.printStackTrace();
            ;
        }
        return vehicle;

    }

    public List<Vehicle> getByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicle = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE year >= ? AND year <= ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, minYear);
                statement.setInt(2, maxYear);

                try (ResultSet results = statement.executeQuery()) {

                    while (results.next()) {
                        String vin = results.getString("vin");
                        int year = results.getInt("year");
                        String make = results.getString("make");
                        String model = results.getString("model");
                        String vehicleType = results.getString("vehicle_type");
                        String color = results.getString("color");
                        int odometer = results.getInt("odometer");
                        double price = results.getDouble("price");

                        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        vehicle.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles by year range: " + e.getMessage());
            e.printStackTrace();
            ;
        }
        return vehicle;

    }

    public List<Vehicle> getByColor(String color) {
        List<Vehicle> vehicle = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE color = ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, color);

                try (ResultSet results = statement.executeQuery()) {

                    while (results.next()) {
                        String vin = results.getString("vin");
                        int year = results.getInt("year");
                        String make = results.getString("make");
                        String model = results.getString("model");
                        String vehicleType = results.getString("vehicle_type");
                        int odometer = results.getInt("odometer");
                        double price = results.getDouble("price");

                        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        vehicle.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles by color: " + e.getMessage());
            e.printStackTrace();
        }
        return vehicle;
    }

    public List<Vehicle> getByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicle = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE odometer >= ? AND odometer <= ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, minMileage);
                statement.setInt(2, maxMileage);

                try (ResultSet results = statement.executeQuery()) {

                    while (results.next()) {
                        String vin = results.getString("vin");
                        int year = results.getInt("year");
                        String make = results.getString("make");
                        String model = results.getString("model");
                        String vehicleType = results.getString("vehicle_type");
                        String color = results.getString("color");
                        int odometer = results.getInt("odometer");
                        double price = results.getDouble("price");

                        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        vehicle.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles by mileage range: " + e.getMessage());
            e.printStackTrace();
            ;
        }
        return vehicle;
    }

    public List<Vehicle> getByType(String vehicleType) {
        List<Vehicle> vehicle = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE vehicle_type = ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, vehicleType);

                try (ResultSet results = statement.executeQuery()) {

                    while (results.next()) {
                        String vin = results.getString("vin");
                        int year = results.getInt("year");
                        String make = results.getString("make");
                        String model = results.getString("model");
                        String color = results.getString("color");
                        int odometer = results.getInt("odometer");
                        double price = results.getDouble("price");

                        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        vehicle.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles by type: " + e.getMessage());
            e.printStackTrace();
            ;
        }
        return vehicle;
    }

    public List<Vehicle> getByVin(String vin) {
        List<Vehicle> vehicle = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE vin = ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, vin);

                try (ResultSet results = statement.executeQuery()) {

                    while (results.next()) {
                        int year = results.getInt("year");
                        String make = results.getString("make");
                        String model = results.getString("model");
                        String vehicleType = results.getString("vehicle_type");
                        String color = results.getString("color");
                        int odometer = results.getInt("odometer");
                        double price = results.getDouble("price");

                        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        vehicle.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles by VIN: " + e.getMessage());
            e.printStackTrace();

        }
        return vehicle;
    }
}
