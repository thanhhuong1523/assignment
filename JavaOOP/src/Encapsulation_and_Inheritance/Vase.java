package Encapsulation_and_Inheritance;

import java.util.Scanner;

public class Vase extends Item {
    private int height;
    private String material;

    public Vase() {}

    public Vase(String id, int value, String creator, int height, String material) {
        super(id, value, creator);
        this.height = height;
        this.material = material;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void input(Scanner scanner) {
        inputHeight(scanner);
        inputMaterial(scanner);
    }

    private void inputHeight(Scanner scanner) {
        String line;
        try {
            while (true) {
                line = scanner.nextLine();
                if(line == null || line.trim().isEmpty()) {
                    System.out.println("Empty height!");
                } else {
                    height = Integer.parseInt(line.trim());
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
    }

    private void inputMaterial(Scanner scanner) {
        while(true) {
            material = scanner.nextLine();
            if(material == null || material.trim().isEmpty()) {
                System.out.println("Empty material");
            } else {
                material = material.trim();
                break;
            }
        }
    }

    public String toString() {
        return String.format(" %5d | %-10s", height, material);
    }
}
