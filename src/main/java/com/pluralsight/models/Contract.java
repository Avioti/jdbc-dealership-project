package com.pluralsight.models;

public abstract class Contract {
    protected String date,customerName,customerEmail;
    protected Vehicle vehicle;
    protected double totalPrice, monthlyPayment;
    protected int ID,vin;

    public Contract(int ID,String date, String customerName, String customerEmail, int vin) {
        this.ID = ID;
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vin = vin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getVin() {
        return vin;
    }
    public void setVin(int vin) {
        this.vin = vin;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}
