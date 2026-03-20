package bikestores.client;

import bikestores.config.DBConnection;
import bikestores.exceptions.ServiceException;
import bikestores.models.Customer;
import bikestores.services.CustomerService;
import bikestores.utils.Constants;
import bikestores.utils.ScannerUtils;
import bikestores.utils.ValidatorUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManagement {
    private final Scanner scanner;
    private final CustomerService customerService;

    public CustomerManagement(Scanner scanner, Connection conn) {
        this.scanner = scanner;
        customerService = new CustomerService(scanner, conn);
    }

    private void insertCustomer() {
        try {
            int id = customerService.insert();

            if(id != -1) {
                System.out.println("Add new customer successfully with customer ID: " + id);
            } else {
                System.out.println("Cannot create new customer!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayAllCustomers(int id, String name, String email, String phone) {
        try {
            ArrayList<Customer> listCustomers = customerService.selectAll(id, name, phone, email);

            if(!listCustomers.isEmpty()) {
                System.out.println(Constants.CUSTOMER_HEADERS());

                for(Customer c : listCustomers) {
                    System.out.println(c);
                }
            } else {
                System.out.println("No customers to display!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterCustomers() {
        int id = ScannerUtils.readPositiveInt(scanner, "Enter customer ID: ", true);
        String name = ScannerUtils.readNullableString(scanner, "Enter customer's name: ");
        String email = ScannerUtils.readNullableString(scanner, "Enter customer's email: ");
        String phone = ScannerUtils.readNullableString(scanner, "Enter customer's phone: ");

        if(phone != null && !ValidatorUtils.isNumber(phone)) {
            System.out.println("Invalid phone number!");
            return;
        }
        displayAllCustomers(id, name, email, phone);
    }

    private void updateCustomer() {
        try {
            Customer customer = customerService.update();

            if(customer != null) {
                System.out.println("Update this customer successfully!");
                System.out.println(Constants.CUSTOMER_HEADERS());
                System.out.println(customer);
            } else {
                System.out.println("Cannot update this customer!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteCustomer() {
        try {
            if(customerService.delete()) {
                System.out.println("Delete customer successfully");
            } else {
                System.out.println("Cannot delete this customer!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n-------- Customer Management -------");
            System.out.println("1. Add new customer");
            System.out.println("2. Display all customers");
            System.out.println("3. Filter customer by ID, name, email or phone");
            System.out.println("4. Edit customer information");
            System.out.println("5. Remove customer by ID");
            System.out.println("6. Quit customer management");

            int choice = ScannerUtils.readPositiveInt(scanner, "Enter your choice: ", false);

            switch (choice) {
                case 1:
                    insertCustomer();
                    break;
                case 2:
                    displayAllCustomers(-1, null, null, null);
                    break;
                case 3:
                    filterCustomers();
                    break;
                case 4:
                    updateCustomer();
                    break;
                case 5:
                    deleteCustomer();
                    break;
                case 6:
                    if(ScannerUtils.quitMenu(scanner)) return;
                    else {
                        System.out.println("Invalid choice!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        Scanner sc = new Scanner(System.in);

        CustomerManagement sm = new CustomerManagement(sc, conn);

        sm.runMenu();
    }
}
