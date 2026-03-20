package bikestores.client;

import bikestores.config.DBConnection;
import bikestores.exceptions.ServiceException;
import bikestores.models.Store;
import bikestores.services.StoreService;
import bikestores.utils.Constants;
import bikestores.utils.ScannerUtils;
import bikestores.utils.ValidatorUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreManagement {
    private final Scanner scanner;
    private final StoreService storeService;

    public StoreManagement(Scanner scanner, Connection conn) {
        this.scanner = scanner;
        storeService = new StoreService(scanner, conn);
    }

    private void insertStore() {
        try {
            int id = storeService.insert();

            if(id != -1) {
                System.out.println("Add new store successfully");
            } else {
                System.out.println("Cannot create new store!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayAllStores (String name, String address, String phone) {
        try {
            ArrayList<Store> listStores = storeService.selectAll(name, address, phone);

            if(!listStores.isEmpty()) {
                System.out.println(Constants.STORE_HEADERS());

                for(Store s : listStores) {
                    System.out.println(s);
                }
            } else {
                System.out.println("No stores to display!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterStores() {
        String name = ScannerUtils.readNullableString(scanner, "Enter store's name: ");
        String address = ScannerUtils.readNullableString(scanner, "Enter store's address: ");
        String phone = ScannerUtils.readNullableString(scanner, "Enter store's phone: ");

        if(phone != null && !ValidatorUtils.isNumber(phone)) {
            System.out.println("Invalid phone number!");
            return;
        }
        displayAllStores(name, address, phone);
    }

    private void updateStore() {
        try {
            Store store = storeService.update();

            if(store != null) {
                System.out.println("Update this store successfully!");
                System.out.println(Constants.STORE_HEADERS());
                System.out.println(store);
            } else {
                System.out.println("Cannot update this staff!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteStore() {
        try {
            if(storeService.delete()) {
                System.out.println("Delete store successfully!");
            } else {
                System.out.println("Cannot delete this store!");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n-------- Store Management -------");
            System.out.println("1. Add new store");
            System.out.println("2. Display all stores");
            System.out.println("3. Filter stores by name, address or phone");
            System.out.println("4. Edit store information");
            System.out.println("5. Remove store by ID");
            System.out.println("6. Quit store management");

            int choice = ScannerUtils.readPositiveInt(scanner, "Enter your choice: ", false);

            switch (choice) {
                case 1:
                    insertStore();
                    break;
                case 2:
                    displayAllStores(null, null, null);
                    break;
                case 3:
                    filterStores();
                    break;
                case 4:
                    updateStore();
                    break;
                case 5:
                    deleteStore();
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

        StoreManagement sm = new StoreManagement(sc, conn);

        sm.runMenu();
    }
}
