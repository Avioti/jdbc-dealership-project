package com.pluralsight.models;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private double salesTax, processingFee;
    private double recordingFee = 100;
    private boolean financeOption;


    public SalesContract(LocalDate date, String customerName, String customerEmail, String vin, double price, boolean financeOption) {
        super(date, customerName, customerEmail, vin, price);
        this.salesTax = 0.05;
        this.processingFee = 295;
        this.recordingFee = 100;
        this.financeOption = financeOption;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public String wantsFinance(Boolean financed) {
        String finance;
        if(financed){
            finance = "YES";
        }else{
            finance = "NO";
        }

        return finance;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public boolean isFinanceOption() {
        return financeOption;
    }

    @Override
    public double getTotalPrice() {

        double salesPrice = vehicle.getPrice() * salesTax;

        if (vehicle.getPrice() >= 10000) {
            processingFee = 495;
        }
        totalPrice = vehicle.getPrice() + salesPrice + recordingFee + processingFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        if (!financeOption) {
            return 0;
        }
        double monthlyRate = 0;

        int months = 0;
        totalPrice = getTotalPrice();
        if (vehicle.getPrice() > 10000) {
            salesTax = 0.0425;
            months = 48;
        } else {
            salesTax = 0.0525;
            months = 24;
        }

        monthlyRate = salesTax / 12;

        monthlyPayment = totalPrice * (monthlyRate * Math.pow(1 + monthlyRate, months))
                / (Math.pow(1 + monthlyRate, months) - 1);


        return monthlyPayment;
    }
}
