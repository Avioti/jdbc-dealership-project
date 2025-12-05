package com.pluralsight.utility;

public class Utility {
    public static void isNumber(String str) {
        try {

            Integer.parseInt(str);

        } catch (Exception e) {
            System.out.println();

            System.out.println("Non Number value Detected " + "\n=====INVALID PRICE=====");

            System.out.println("=====NUMBERS ONLY=====");

        }
    }

    public static boolean ifNumber(String str) {
        try {

            Integer.parseInt(str);
            return true;

        } catch (Exception e) {
            return false;

        }
    }

}
