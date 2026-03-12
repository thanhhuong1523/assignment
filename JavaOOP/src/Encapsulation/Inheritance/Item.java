package Encapsulation.Inheritance;

import java.util.Scanner;

public class Item {
    protected String id;
    protected int value;
    protected String creator;

    // Constructor with no params
    public Item() {}

    // Constructor with params
    public Item(String id, int value, String creator) {
        this.id = id;
        this.value = value;
        this.creator = creator;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    // Method to input id, value and creator
    public void input(Scanner scanner) {
        id = inputString(scanner,"Enter ID: ");
        value = inputNumber(scanner, "Enter value: ");
        creator = inputString(scanner, "Enter creator: ");
    }

    /**
     * Method to input a number
     * @return int
     */
    public int inputNumber(Scanner scanner, String prompt) {
        String line;
        try {
            while (true) {
                System.out.print(prompt);

                line = scanner.nextLine();
                if(line == null || line.trim().isEmpty()) {
                    System.out.println("Empty value!");
                } else {
                    return Integer.parseInt(line.trim());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
            return 0;
        }
    }

    /**
     * Method to input a string
     * @return String
     */
    public String inputString(Scanner scanner, String prompt) {
        String line;

        while (true) {
            System.out.print(prompt);

            line = scanner.nextLine();
            if(line == null || line.trim().isEmpty()) {
                System.out.println("Empty value!");
            } else {
                return line.trim();
            }
        }
    }

    /**
     * Method to input a string
     * @return boolean
     */
    public boolean inputBoolean(Scanner scanner, String prompt) {
        String line;
        while (true) {
            System.out.print(prompt);
            line = scanner.nextLine();

            if(line == null || line.trim().isEmpty()) {
                System.out.println("Empty value!");
            } else if(!line.trim().equalsIgnoreCase("true") && !line.trim().equalsIgnoreCase("false")) {
                System.out.println("Invalid value");
            } else {
                return Boolean.parseBoolean(line.trim().toLowerCase());
            }
        }
    }

    // return type of item
    public String getTypeOfItem() {
        return "item";
    }

    // return header to display items
    public String header() {
        return String.format("%-8s | %-5s | %-10s", "ID", "Value", "Creator");
    }

    @Override
    public String toString() {
        return String.format("%-8s | %5d | %-10s", id, value, creator);
    }
}
