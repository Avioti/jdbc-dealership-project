package com.pluralsight.data;

import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;

import java.sql.*;

public class ContractDao {

    private final DataManager dataManager;

    public ContractDao(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    public void saveLeaseContract(LeaseContract contract) {

        String query = "INSERT INTO lease_contracts (vin,lease_date,customer_name,customer_email,finance_option) " +
                "VALUES (?,?,?,?,?)";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, contract.getVin());
                statement.setDate(2, Date.valueOf(contract.getDate()));
                statement.setString(3, contract.getCustomerName());
                statement.setString(4, contract.getCustomerEmail());
                statement.setBoolean(5, contract.isLeaseToOwn());

                statement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveSalesContract(SalesContract contract) {

        String query = "INSERT INTO sales_contracts (vin,sale_date,customer_name,customer_email,price,finance_option) " +
                "VALUES (?,?,?,?,?,?)";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, contract.getVin());
                statement.setDate(2, Date.valueOf(contract.getDate()));
                statement.setString(3, contract.getCustomerName());
                statement.setString(4, contract.getCustomerEmail());
                statement.setDouble(5, contract.getPrice());
                statement.setBoolean(6, contract.isFinanceOption());

                statement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
