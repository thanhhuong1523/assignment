package Encapsulation.Inheritance;

import java.util.Scanner;

public class AntiqueShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItemList itemList = new ItemList();

        // main program
        while (true) {
            System.out.println("------ Antique Shop ------");
            System.out.println("1. Add item");
            System.out.println("2. Display all items");
            System.out.println("3. Find item by creator");
            System.out.println("4. Display items by type");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            if(choice == null || choice.trim().isEmpty()) {
                System.out.println("Empty choice!");
            } else {
                choice = choice.trim();

                switch (choice) {
                    case "1":
                        if(itemList.addItem(scanner)) {
                            System.out.println("Add new item successfully!");
                        } else {
                            System.out.println("Cannot add this item!");
                        }
                        break;
                    case "2":
                        itemList.displayAll();
                        break;
                    case "3":
                        Item item = new Item();
                        String creator = item.inputString(scanner, "Enter creator: ");
                        if(itemList.findItem(creator) != null) {
                            System.out.println(itemList.findItem(creator));
                        } else {
                            System.out.println("No item found!");
                        }
                        break;
                    case "4":
                        itemList.displayItemsByType(scanner);
                        break;
                    case "0":
                        System.out.print("Are you sure to exit? Please enter 1 to confirm! (1/0): ");
                        String opt = scanner.nextLine();
                        if(opt != null && opt.trim().equals("1")) {
                            System.exit(0);
                        } else {
                            break;
                        }
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        }
    }
}
