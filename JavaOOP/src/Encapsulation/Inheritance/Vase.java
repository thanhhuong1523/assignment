package Encapsulation.Inheritance;

import java.util.Scanner;

public class Vase extends Item {
    private int height;
    private String material;

    // constructor with no params
    public Vase() {}

    // constructor with params
    public Vase(String id, int value, String creator, int height, String material) {
        super(id, value, creator);
        this.height = height;
        this.material = material;
    }

    // getters and setters
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

    @Override
    public void input(Scanner scanner) {
        super.input(scanner);
        height = inputNumber(scanner, "Enter height: ");
        material = inputString(scanner, "Enter material: ");
    }

    @Override
    public String getTypeOfItem() {
        return "Vase";
    }

    @Override
    public String header() {
        return String.format("%-8s | %-5s | %-10s | %-5s | %-10s", "ID", "Value", "Creator", "Height", "Material");
    }

    @Override
    public String toString() {
        return String.format("%-8s | %5d | %-10s | %5d | %-10s", id, value, creator, height, material);
    }
}
