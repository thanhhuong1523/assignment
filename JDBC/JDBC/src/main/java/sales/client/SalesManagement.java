package sales.client;

import sales.dao.CustomerDAO;
import sales.entities.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesManagement {
    private final Scanner sc;
    private final CustomerDAO customerDAO;
    private final CustomerForm customerForm;

    public SalesManagement() {
        Connection conn = getConnection();

        sc = new Scanner(System.in);
        customerDAO = new CustomerDAO(conn);
        customerForm = new CustomerForm(sc);
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sales", "root", "123456"
            );
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }

        return null;
    }

    private void displayAllCustomers() {
        try {
            ArrayList<Customer> listCustomers = customerDAO.selectAll();

            if(listCustomers.isEmpty()) {
                System.out.println("No customers");
                return;
            }

            System.out.println(header());
            for(Customer c : listCustomers) {
                System.out.println(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addCustomer() {
        try {
            Customer customer = customerForm.getCustomer();

            if(customerDAO.insert(customer)) {
                System.out.println("Add new customer successfully!");
            } else {
                System.out.println("Cannot add this customer!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeCustomer() {
        try {
            int id = customerForm.getID();
            Customer customer = customerDAO.getByID(id);

            if(customer == null) {
                System.out.println("ID not found!");
                return;
            }

            if(customerDAO.delete(id)) {
                System.out.println("Delete this customer successfully!");
            } else {
                System.out.println("Cannot delete this customer!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateCustomer() {
        try {
            int id = customerForm.getID();
            Customer customer = customerDAO.getByID(id);

            if(customer == null) {
                System.out.println("ID not found!");
                return;
            }
            Customer newCustomer = customerForm.getCustomer();

            if(customerDAO.update(id, newCustomer)) {
                System.out.println("Update this customer successfully!");
            } else {
                System.out.println("Cannot update this customer!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String header() {
        return String.format("%-5s | %-10s | %-10s | %-10s | %-10s | %-12s | %-10s",
                "ID", "Name", "Contact", "Address", "City", "Postal Code", "Country");
    }

    public static void main(String[] args) {
        SalesManagement sm = new SalesManagement();

        while (true) {
            System.out.println("\n------ Sales Management ------");
            System.out.println("1. Get all customers");
            System.out.println("2. Add new an customer");
            System.out.println("3. Change customer information");
            System.out.println("4. Remove an customer");
            System.out.println("0. Quit");

            String choice = sm.customerForm.readNonEmpty("Enter your choice: ");

            switch (choice) {
                case "1":
                    sm.displayAllCustomers();
                    break;
                case "2":
                    sm.addCustomer();
                    break;
                case "3":
                    sm.updateCustomer();
                    break;
                case "4":
                    sm.removeCustomer();
                    break;
                case "0":
                    System.out.print("Are you sure to exit? Please enter 1 to confirm! (1/0): ");
                    String opt = sm.sc.nextLine();
                    if(opt != null && opt.trim().equals("1")) {
                        System.out.println("Goodbye!");
                        sm.sc.close();
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
