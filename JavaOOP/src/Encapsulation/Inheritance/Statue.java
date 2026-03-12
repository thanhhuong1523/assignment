package Encapsulation.Inheritance;

import java.util.Scanner;

public class Statue extends Item {
    private int weight;
    private String color;

    // constructor with no params
    public Statue() {}

    // constructor with params
    public Statue(String id, int value, String creator, int weight, String color) {
        super(id, value, creator);
        this.weight = weight;
        this.color = color;
    }

    //getters and setters
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void input(Scanner scanner) {
        super.input(scanner);
        weight = inputNumber(scanner, "Enter weight: ");
        color = inputString(scanner, "Enter color: ");
    }

    @Override
    public String getTypeOfItem() {
        return "Statue";
    }

    @Override
    public String header() {
        return String.format("%-8s | %-5s | %-10s | %-5s | %-10s", "ID", "Value", "Creator", "Weight", "Color");
    }

    @Override
    public String toString() {
        return String.format("%-8s | %5d | %-10s | %5d | %-10s", id, value, creator, weight, color);
    }
}
