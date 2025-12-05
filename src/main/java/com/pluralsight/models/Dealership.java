package com.pluralsight.models;




import java.util.ArrayList;


public class Dealership {
    private String name,address,phone;
    private int ID;
    private ArrayList<Vehicle> inventory;
    private ArrayList<Vehicle> matches;

    public Dealership(int ID,String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.ID = ID;
        inventory = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }






    @Override
    public String toString() {
        return String.format("%s|%s|%s",this.name,this.address,this.phone);
    }
}
