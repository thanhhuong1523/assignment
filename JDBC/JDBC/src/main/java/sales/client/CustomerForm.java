package sales.client;

import sales.entities.Customer;
import sales.utils.ScannerUtils;

import java.util.Scanner;

public class CustomerForm {
    private final Scanner sc;

    public CustomerForm(Scanner sc) {
        this.sc = sc;
    }

    public int getID() throws NumberFormatException {
        return ScannerUtils.readPositiveInt(sc,"Enter customer ID: ");
    }

    public Customer getCustomer() {
        String name = ScannerUtils.readNonEmpty(sc, "Enter customer's name: ");
        String contact = ScannerUtils.readNonEmpty(sc, "Enter contact name: ");
        String address = ScannerUtils.readNonEmpty(sc, "Enter customer's address: ");
        String city = ScannerUtils.readNonEmpty(sc, "Enter customer's city: ");
        String postCode = ScannerUtils.readNonEmpty(sc, "Enter customer's postal code: ");
        String country = ScannerUtils.readNonEmpty(sc, "Enter customer's country: ");

        return new Customer(0, name, contact, address, city, postCode, country);
    }
}
