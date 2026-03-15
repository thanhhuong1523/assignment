package sales.client;

import sales.dao.CustomerDAO;
import sales.dao.EmployeeDAO;
import sales.entities.Customer;
import sales.entities.Employee;
import sales.utils.ScannerUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesManagement {
    private final Scanner sc;
    private final CustomerDAO customerDAO;
    private final EmployeeDAO employeeDAO;
    private final CustomerForm customerForm;
    private final EmployeeForm employeeForm;

    public SalesManagement() {
        Connection conn = getConnection();

        sc = new Scanner(System.in);
        customerDAO = new CustomerDAO(conn);
        employeeDAO = new EmployeeDAO(conn);
        customerForm = new CustomerForm(sc);
        employeeForm = new EmployeeForm(sc);
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sales";
            String user = "root";
            String password = "123456";

            return DriverManager.getConnection(url, user, password);
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

            System.out.println(customerHeader());
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

    private void displayAllEmployees() {
        try {
            ArrayList<Employee> listEmployees = employeeDAO.selectAll();

            if(listEmployees.isEmpty()) {
                System.out.println("No employees!");
                return;
            }

            System.out.println(employeeHeader());
            for(Employee e : listEmployees) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addEmployee() {
        try {
            Employee employee = employeeForm.getEmployee();

            if(employeeDAO.insert(employee)) {
                System.out.println("Add new employee successfully!");
            } else {
                System.out.println("Cannot add this employee!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeEmployee() {
        try {
            int id = employeeForm.getID();
            Employee employee = employeeDAO.getByID(id);

            if(employee == null) {
                System.out.println("ID not found!");
                return;
            }

            if(employeeDAO.delete(id)) {
                System.out.println("Remove this employee successfully!");
            } else {
                System.out.println("Cannot remove this employee!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateEmployee() {
        try {
            int id = employeeForm.getID();

            if(employeeDAO.getByID(id) == null) {
                System.out.println("ID not found!");
                return;
            }

            Employee employee = employeeForm.getEmployee();
            if(employeeDAO.update(id, employee)) {
                System.out.println("Update this employee successfully!");
            } else {
                System.out.println("Cannot update this employee!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String customerHeader() {
        return String.format("%-5s | %-10s | %-10s | %-10s | %-10s | %-12s | %-10s",
                "ID", "Name", "Contact", "Address", "City", "Postal Code", "Country");
    }

    private String employeeHeader() {
        return String.format("%-5s | %-12s | %-10s | %-10s | %-5s",
                "ID", "Last Name", "First Name", "Birthday", "Supervisor ID");
    }

    public static void main(String[] args) {
        SalesManagement sm = new SalesManagement();

        while (true) {
            System.out.println("\n------ Sales Management ------");
            System.out.println("1. Get all customers");
            System.out.println("2. Add new an customer");
            System.out.println("3. Change customer information");
            System.out.println("4. Remove an customer");
            System.out.println("5. Get all employees");
            System.out.println("6. Add new an employee");
            System.out.println("7. Change employee information");
            System.out.println("8. Remove an employee");
            System.out.println("0. Quit");

            String choice = ScannerUtils.readNonEmpty(sm.sc, "Enter your choice: ");

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
                case "5":
                    sm.displayAllEmployees();
                    break;
                case "6":
                    sm.addEmployee();
                    break;
                case "7":
                    sm.updateEmployee();
                    break;
                case "8":
                    sm.removeEmployee();
                    break;
                case "0":
                    if(ScannerUtils.quitMenu(sm.sc)) {
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
