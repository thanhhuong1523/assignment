package sales.client;

import sales.entities.Employee;
import sales.utils.ScannerUtils;

import java.util.Scanner;

public class EmployeeForm {
    private final Scanner sc;

    public EmployeeForm(Scanner sc) {
        this.sc = sc;
    }

    public int getID() throws NumberFormatException {
        return ScannerUtils.readPositiveInt(sc, "Enter employee ID: ");
    }

    public Employee getEmployee() throws NumberFormatException, Exception {
        String lastName = ScannerUtils.readNonEmpty(sc, "Enter employee's last name: ");
        String firstName = ScannerUtils.readNonEmpty(sc, "Enter employee's first name: ");
        String birthday = ScannerUtils.readDate(sc, "Enter employee's birthday (yyyy-MM-dd): ");
        int supervisor = ScannerUtils.readPositiveInt(sc, "Enter supervisor ID: ");

        return new Employee(0, lastName, firstName, birthday, supervisor);
    }
}
