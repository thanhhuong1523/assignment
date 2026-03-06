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
        inputNumber(scanner, height);
        inputMaterial(scanner);
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
        return String.format("%-8s | %5d | %-10s | %5d | %-10s", id, value, creator, height, material);
    }
}
