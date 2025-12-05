package com.pluralsight.utility;
public class ColorCodes 
{
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    private ColorCodes(){}




    // Bold
    public static final String BOLD = "\033[1m";

    // Bold High Intensity
    public static final String BOLD_WHITE = "\033[1;97m";

    /**
     * Prints a header with color
     */
    public static void printHeader(String text) {
        System.out.println(BOLD + CYAN + "\n" + "=".repeat(70) + RESET);
        System.out.println(BOLD + CYAN + text + RESET);
        System.out.println(BOLD + CYAN + "=".repeat(70) + RESET);
    }

    /**
     * Prints a section header
     */
    public static void printSection(String text) {
        System.out.println(BOLD + "\n" + text + RESET);
        System.out.println("-".repeat(text.length()));
    }

    /**
     * Prints success message
     */
    public static void printSuccess(String text) {
        System.out.println(GREEN + "✓ " + text + RESET);
    }

    /**
     * Prints error message
     */
    public static void printError(String text) {
        System.out.println(RED + "✗ " + text + RESET);
    }

    /**
     * Prints warning message
     */
    public static void printWarning(String text) {
        System.out.println(YELLOW + "⚠ " + text + RESET);
    }
    
}
