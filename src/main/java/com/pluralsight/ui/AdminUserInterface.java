package com.pluralsight.ui;

import com.pluralsight.models.Contract;

import java.util.ArrayList;

import static com.pluralsight.ui.UserInterface.scanner;
import static com.pluralsight.utility.Utility.ifNumber;

public class AdminUserInterface{
    private String password;



    public AdminUserInterface(String password) {
        this.password = password;
    }

    public void display(){
        System.out.println("1 - List all Contracts");
        System.out.println("2 - List last 10 Contracts");
        System.out.println("0 - Exit to main menu");
        adminInput();


    }

    public void adminInput(){
        String option = scanner.nextLine().trim();
        boolean running = true;
        while(running){
            if (ifNumber(option)) {
                int choice = Integer.parseInt(option);
                switch (choice) {
                    case 1 -> listAllContracts();

                    case 2 -> listLastNContracts();

                    case 3 -> running = false;

                    default -> System.out.println("Enter valid option");

                }
            } else {
                System.out.println();
                System.out.println("Enter valid option");
            }
        }


    }

    public boolean authenticate(String input){
        if(input.equalsIgnoreCase(password)){
            return true;
        }
        System.out.println("Invalid Password");
        return false;
    }

    public void listAllContracts(){



    }

    public ArrayList<Contract> listLastNContracts(){
        return null;

    }

    public void showContractDetails(Contract c){

    }
}
