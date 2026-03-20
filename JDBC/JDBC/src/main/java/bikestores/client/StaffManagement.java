package bikestores.client;

import bikestores.config.DBConnection;
import bikestores.exceptions.ServiceException;
import bikestores.models.Staff;
import bikestores.services.StaffService;
import bikestores.utils.Constants;
import bikestores.utils.ScannerUtils;
import bikestores.utils.ValidatorUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class StaffManagement {
    private final Scanner scanner;
    private final StaffService staffService;

    public StaffManagement(Scanner scanner, Connection conn) {
        this.scanner = scanner;
        staffService = new StaffService(scanner, conn);
    }

    private void insertStaff() {
        try {
            int id = staffService.insert();

            if(id != -1) {
                System.out.println("Add new staff successfully with staffID: " + id);
            } else {
                System.out.println("Cannot create new staff!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayAllStaffs(String name, String role, int storeID) {
        try {
            ArrayList<Staff> listStaffs = staffService.selectAll(name, role, storeID);

            if(!listStaffs.isEmpty()) {
                System.out.println(Constants.STAFF_HEADERS());
                for(Staff s : listStaffs) {
                    System.out.println(s);
                }
            } else {
                System.out.println("No staffs to display!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterStaffs() {
        String name = ScannerUtils.readNullableString(scanner, "Enter name: ");
        String role = ScannerUtils.readNullableString(scanner, "Enter role: ");
        String storeIDString = ScannerUtils.readNullableString(scanner, "Enter store ID: ");

        if(role != null) role = role.toLowerCase(Locale.ROOT);
        if(storeIDString != null && !ValidatorUtils.isNumber(storeIDString)) {
            System.out.println("Invalid store ID!");
            return;
        }

        int storeID = storeIDString == null ? -1 : Integer.parseInt(storeIDString);

        displayAllStaffs(name, role, storeID);
    }

    private void updateStaff() {
        try {
            Staff staff = staffService.update();
            if(staff != null) {
                System.out.println("Update this staff successfully!");
                System.out.println(Constants.STAFF_HEADERS());
                System.out.println(staff);
            } else {
                System.out.println("Cannot update this staff!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteStaff() {
        try {
            if(staffService.delete()) {
                System.out.println("Delete staff successfully!");
            } else {
                System.out.println("Cannot delete this staff!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n-------- Staff Management -------");
            System.out.println("1. Add new staff");
            System.out.println("2. Display all staffs");
            System.out.println("3. Filter staffs by name, role or store");
            System.out.println("4. Edit staff information");
            System.out.println("5. Remove staff by ID");
            System.out.println("6. Quit staff management");

            int choice = ScannerUtils.readPositiveInt(scanner, "Enter your choice: ", false);

            switch (choice) {
                case 1:
                    insertStaff();
                    break;
                case 2:
                    displayAllStaffs(null, null, -1);
                    break;
                case 3:
                    filterStaffs();
                    break;
                case 4:
                    updateStaff();
                    break;
                case 5:
                    deleteStaff();
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

        StaffManagement sm = new StaffManagement(sc, conn);

        sm.runMenu();
    }
}
