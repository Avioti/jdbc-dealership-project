package com.pluralsight.data;

import com.pluralsight.models.Contract;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ContractFileManager {
    private static final String contractPath = "src/main/resources/contracts.csv";
    public ArrayList<String> contracts = new ArrayList<String>();

    public void saveContract(Contract c) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(contractPath, true));

            if (c instanceof SalesContract sales) {
                String sale = String.format(
                        "SALE|%s|%s|%s|%d|" +
                                "%d|%s|%s|%s|%s|%d|%.2f" +
                                "|%.2f|%.2f|%.2f|%.2f|%s|%.2f"
                        , sales.getDate(), sales.getCustomerName(), sales.getCustomerEmail(), sales.getVehicle().getVin(),
                        sales.getVehicle().getYear(), sales.getVehicle().getMake(), sales.getVehicle().getModel(), sales.getVehicle().getVehicleType(),
                        sales.getVehicle().getColor(), sales.getVehicle().getOdometer(), sales.getVehicle().getPrice(),
                        sales.getSalesTax(), sales.getRecordingFee(), sales.getProcessingFee(), sales.getTotalPrice(), sales.wantsFinance(sales.isFinanceOption()),
                        sales.getMonthlyPayment());
                writer.newLine();
                writer.write(sale);
                writer.close();
            }

            if (c instanceof LeaseContract leases) {
                String lease = String.format(
                        "LEASE|%s|%s|%s|%d|" +
                                "%d|%s|%s|%s|%s|%d|%.2f" +
                                "|%.2f|%.2f|%.2f|%.2f"
                        , leases.getDate(), leases.getCustomerName(), leases.getCustomerEmail(), leases.getVehicle().getVin(),
                        leases.getVehicle().getYear(), leases.getVehicle().getMake(), leases.getVehicle().getModel(), leases.getVehicle().getVehicleType(),
                        leases.getVehicle().getColor(), leases.getVehicle().getOdometer(), leases.getVehicle().getPrice(),
                        leases.getExpectedEndingValue(), leases.getLeaseFee(), leases.getTotalPrice(), leases.getMonthlyPayment());
                writer.newLine();
                writer.write(lease);
                writer.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public ArrayList<String> readContracts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(contractPath))) {
            String input;
            while ((input = reader.readLine()) != null) {
                if (input.isBlank()) continue;

                String[] contractLines = input.split("\\|");
                String contractType = contractLines[0].trim();

                if ("SALE".equalsIgnoreCase(contractType)) {
                    if (contractLines.length < 18) {

                        continue;
                    }

                    String date = contractLines[1];
                    String name = contractLines[2];
                    String email = contractLines[3];
                    String vin = contractLines[4];
                    String year = contractLines[5];
                    String make = contractLines[6];
                    String model = contractLines[7];
                    String type = contractLines[8];
                    String color = contractLines[9];
                    String odometer = contractLines[10];
                    String price = contractLines[11];

                    String salesTax = contractLines[12];
                    String recordingFee = contractLines[13];
                    String processingFee = contractLines[14];
                    String totalPrice = contractLines[15];
                    String financed = contractLines[16];
                    String monthlyPayment = contractLines[17];

                    contracts.add(String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",
                            "SALE", date, name, email, vin,
                            year, make, model, type, color, odometer, price,
                            salesTax, recordingFee, processingFee, totalPrice, financed, monthlyPayment));

                } else if ("LEASE".equalsIgnoreCase(contractType)) {
                    if (contractLines.length < 16) {
                        continue;
                    }
                    String date = contractLines[1];
                    String name = contractLines[2];
                    String email = contractLines[3];
                    String vin = contractLines[4];
                    String year = contractLines[5];
                    String make = contractLines[6];
                    String model = contractLines[7];
                    String type = contractLines[8];
                    String color = contractLines[9];
                    String odometer = contractLines[10];
                    String price = contractLines[11];
                    String expectedEndingValue = contractLines[12];
                    String leaseFee = contractLines[13];
                    String totalPrice = contractLines[14];
                    String monthlyPayment = contractLines[15];

                    contracts.add(String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",
                            "LEASE", date, name, email, vin,
                            year, make, model, type, color, odometer, price,
                            expectedEndingValue, leaseFee, totalPrice, monthlyPayment));
                } else {

                    System.err.println("Unknown contract type: " + input);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contracts;
    }


}
