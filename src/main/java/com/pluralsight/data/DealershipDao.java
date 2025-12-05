package com.pluralsight.data;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDao {

    private final DataManager dataManager;

    public DealershipDao(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Dealership> getDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM dealerships";

        try (Connection connection = dataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");

                Dealership dealership = new Dealership(id, name, address, phone);
                dealerships.add(dealership);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dealerships;
    }

    public List<Dealership> getDealershipName(String name) {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM dealerships WHERE Name LIKE ?";

        try (Connection connection = dataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String dealershipName = resultSet.getString("Name");
                    String address = resultSet.getString("Address");
                    String phone = resultSet.getString("Phone");

                    Dealership dealership = new Dealership(id, dealershipName, address, phone);
                    dealerships.add(dealership);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }

    public List<Dealership> getDealershipByID(int id) {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM dealerships WHERE ID = ?";

        try (Connection connection = dataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String address = resultSet.getString("Address");
                    String phone = resultSet.getString("Phone");

                    Dealership dealership = new Dealership(id, name, address, phone);
                    dealerships.add(dealership);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return dealerships;
    }


}
