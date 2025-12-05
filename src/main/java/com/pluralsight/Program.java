package com.pluralsight;


import com.pluralsight.config.DatabaseConfig;
import com.pluralsight.data.ContractDao;
import com.pluralsight.data.DataManager;
import com.pluralsight.data.DealershipDao;
import com.pluralsight.data.VehicleDao;
import com.pluralsight.ui.UserInterface;

public class Program {

    public static void main(String[] args) {
        System.out.println("Initializing Dealership Database Tool...\n");

        DataManager dataManager = null;

        try {
            dataManager = new DataManager();

            VehicleDao vehicleDao = new VehicleDao(dataManager);
            DealershipDao dealershipDao = new DealershipDao(dataManager);
            ContractDao contractDao = new ContractDao(dataManager);
            UserInterface ui = new UserInterface(vehicleDao, contractDao,dealershipDao );

            ui.display();


        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (dataManager != null) {
                dataManager.close();
            }
            DatabaseConfig.closeDataSource();
        }
    }
}