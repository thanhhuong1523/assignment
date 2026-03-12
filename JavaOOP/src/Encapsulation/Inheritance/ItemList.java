package Encapsulation.Inheritance;

import java.util.Scanner;

public class ItemList {
    private final Item[] listItems;
    private int numOfItem = 0;
    private final int MAX = 100;

    // constructor with no params, initial array listItems
    public ItemList() {
        listItems = new Item[MAX];
    }

    /**
     * Add a new item to listItems
     * @return boolean
     */
    public boolean addItem(Scanner scanner) {
        if(numOfItem == MAX) return false;

        Item item = createItem(scanner);

        if(item != null && getIndexByID(item.getId()) == -1) {
            listItems[numOfItem++] = item;
            return true;
        }

        return false;
    }

    /**
     * Choose type of item and create a new one
     * @return Item
     */
    private Item createItem(Scanner scanner) {
        Item item = null;

        System.out.println("Which item do you want to add?");
        System.out.println("1. Vase");
        System.out.println("2. Statue");
        System.out.println("3. Painting");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();
        if(choice == null || choice.trim().isEmpty()) {
            System.out.println("Empty choice");
        } else {
            switch (choice) {
                case "1":
                    item = new Vase();
                    item.input(scanner);
                    break;
                case "2" :
                    item = new Statue();
                    item.input(scanner);
                    break;
                case "3":
                    item = new Painting();
                    item.input(scanner);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        return item;
    }

    /**
     * return the index of item by ID
     * @return int
     */
    private int getIndexByID(String id) {
        if(numOfItem == 0) return -1;

        for(int i = 0; i < numOfItem; i++) {
            if(listItems[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    // Display all items of each type
    public void displayAll() {
        if(numOfItem == 0) {
            System.out.println("No items");
            return;
        }

        displayTypedItems("Vase");
        System.out.println();

        displayTypedItems("Statue");
        System.out.println();

        displayTypedItems("Painting");
    }

    /**
     * return Item by param creator
     * @return Item
     */
    public Item findItem(String creator) {
        for(int i = 0; i < numOfItem; i++) {
            if(listItems[i].getCreator().contains(creator)) {
                return listItems[i];
            }
        }
        return null;
    }

    // Choose type and display items of that type
    public void displayItemsByType(Scanner scanner) {
        System.out.println("Which item do you want to display?");
        System.out.println("1. Vase");
        System.out.println("2. Statue");
        System.out.println("3. Painting");

        String choice = scanner.nextLine();
        if(choice == null || choice.trim().isEmpty()) {
            System.out.println("Empty choice");
        } else {
            switch (choice) {
                case "1":
                    displayTypedItems("Vase");
                    break;
                case "2" :
                    displayTypedItems("Statue");
                    break;
                case "3":
                    displayTypedItems("Painting");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    // Display item of the type selected
    private void displayTypedItems(String type) {
        int flag = 0;

        for(int i = 0; i < numOfItem; i++) {
            if(listItems[i].getTypeOfItem().equalsIgnoreCase(type)) {
                if(flag == 0) {
                    System.out.println(type + ":");
                    System.out.println(listItems[i].header());
                }
                System.out.println(listItems[i]);
                flag = 1;
            }
        }

        if(flag == 0) {
            System.out.println("No " + type + " items!");
        }
    }
}
