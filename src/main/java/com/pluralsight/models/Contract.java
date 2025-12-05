package com.pluralsight.models;

import java.time.LocalDate;

public abstract class Contract {
    protected LocalDate date;
    protected String customerName,customerEmail;
    protected Vehicle vehicle;
    protected double totalPrice, monthlyPayment;
    protected double price;
    protected String vin;

    public Contract(LocalDate date, String customerName, String customerEmail, String vin, double price) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vin = vin;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getVin() {
        return vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}
