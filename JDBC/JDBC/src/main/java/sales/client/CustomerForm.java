package sales.client;

import sales.entities.Customer;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerForm {
    private Scanner sc;

    public CustomerForm(Scanner sc) {
        this.sc = sc;
    }

    public int getID() throws NumberFormatException {
        return readPositiveInt("Enter customer ID: ");
    }

    public Customer getCustomer() {
        String name = readNonEmpty("Enter customer's name: ");
        String contact = readNonEmpty("Enter contact name: ");
        String address = readNonEmpty("Enter customer's address: ");
        String city = readNonEmpty("Enter customer's city: ");
        String postCode = readNonEmpty("Enter customer's postal code: ");
        String country = readNonEmpty("Enter customer's country: ");

        return new Customer(0, name, contact, address, city, postCode, country);
    }

    public String readNonEmpty(String prompt) {
        String line;

        while(true) {
            System.out.print(prompt);
            line = sc.nextLine();

            if(line == null || line.trim().isEmpty()) {
                System.out.println("Empty value!");
            } else break;
        }

        return line.trim();
    }

    public int readPositiveInt(String prompt) {
        String line;

        while (true) {
            try {
                line = readNonEmpty(prompt);
                int num = Integer.parseInt(line);

                if(num <= 0) {
                    System.out.println("Please enter positive number!");
                } else return num;

            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
    }
}
