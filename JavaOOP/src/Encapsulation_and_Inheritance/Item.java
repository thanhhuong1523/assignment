package Encapsulation_and_Inheritance;

import java.util.Scanner;

public class Item {
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
        inputNumber(scanner, value);
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

    public void inputNumber(Scanner scanner, int num) {
        String line;
        try {
            while (true) {
                line = scanner.nextLine();
                if(line == null || line.trim().isEmpty()) {
                    System.out.println("Empty value!");
                } else {
                    num = Integer.parseInt(line.trim());
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
    }

    @Override
    public String toString() {
        return String.format("%-8s | %5d | %-10s", id, value, creator);
    }
}
