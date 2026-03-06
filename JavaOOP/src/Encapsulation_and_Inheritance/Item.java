package Encapsulation_and_Inheritance;

import java.util.Scanner;

abstract public class Item {
    protected String id;
    protected int value;
    protected String creator;

    public Item() {}

    public Item(String id, int value, String creator) {
        this.id = id;
        this.value = value;
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void input(Scanner scanner) {
        inputID(scanner);
        inputValue(scanner);
        inputValue(scanner);
    }

    private void inputID(Scanner scanner) {
        while(true) {
            id = scanner.nextLine();
            if(id == null || id.trim().isEmpty()) {
                System.out.println("Empty ID!");
            } else {
                id = id.trim();
                break;
            }
        }
    }

    private void inputValue(Scanner scanner) {
        String line;
        try {
            while(true) {
                line = scanner.nextLine();
                if(line == null || line.trim().isEmpty()) {
                    System.out.println("Empty value!");
                } else {
                    value = Integer.parseInt(line.trim());
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
    }

    private void inputCreator(Scanner scanner) {
        while (true) {
            creator = scanner.nextLine();
            if(creator == null || creator.trim().isEmpty()) {
                System.out.println("Empty creator!");
            } else {
                creator = creator.trim();
                break;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%-8s | %5d | %-10s", id, value, creator);
    }
}
