package com.pluralsight.models;

import java.time.LocalDate;

public class LeaseContract extends Contract{
    private double expectedEndingValue,leaseFee;
    private boolean isLeaseToOwn;

    public LeaseContract(LocalDate date, String customerName, String customerEmail, String vin, double price, boolean isLeaseToOwn) {
        super(date, customerName, customerEmail, vin, price);

    this.expectedEndingValue = getExpectedEndingValue();
    this.leaseFee = getLeaseFee();
    }

    public double getExpectedEndingValue() {
        return 0.50 * vehicle.getPrice();
    }

    public double getLeaseFee() {
        return 0.07 * vehicle.getPrice();
    }

    public boolean isLeaseToOwn() {
        return isLeaseToOwn;
    }

    @Override
    public double getTotalPrice() {

        return getExpectedEndingValue() + getLeaseFee();

    }

    @Override
    public double getMonthlyPayment() {

        double monthlyRate = 0;

        int months = 0;
        totalPrice = getTotalPrice();
            months = 36;

        monthlyRate = 0.04 / 12;

        monthlyPayment = totalPrice * (monthlyRate * Math.pow(1 + monthlyRate, months))
                / (Math.pow(1 + monthlyRate, months) - 1);


        return monthlyPayment;
    }
}
