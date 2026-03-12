package Encapsulation.Inheritance;

import java.util.Scanner;

public class Painting extends Item {
    private int height;
    private int width;
    private boolean isWaterColor;
    private boolean isFramed;

    // constructor with no params
    public Painting() {}

    // constructor with params
    public Painting(String id, int value, String creator, int height, int width, boolean isWaterColor, boolean isFramed) {
        super(id, value, creator);
        this.height = height;
        this.width = width;
        this.isWaterColor = isWaterColor;
        this.isFramed = isFramed;
    }

    // getters and setters
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isWaterColor() {
        return isWaterColor;
    }

    public void setWaterColor(boolean waterColor) {
        isWaterColor = waterColor;
    }

    public boolean isFramed() {
        return isFramed;
    }

    public void setFramed(boolean framed) {
        isFramed = framed;
    }

    @Override
    public void input(Scanner scanner) {
        super.input(scanner);
        height = inputNumber(scanner, "Enter height: ");
        width = inputNumber(scanner, "Enter width: ");
        isWaterColor = inputBoolean(scanner, "Enter isWaterColor: ");
        isFramed = inputBoolean(scanner, "Enter isFramed: ");
    }

    @Override
    public String getTypeOfItem() {
        return "Painting";
    }

    @Override
    public String header() {
        return String.format("%-8s | %-5s | %-10s | %-5s | %-5s | %-5s | %-5s",
                "ID", "Value", "Creator", "Height", "Weight", "IsWaterColor", "IsFramed");
    }

    @Override
    public String toString() {
        return String.format("%-8s | %5d | %-10s | %5d | %5d | %-5s | %-5s",
                id, value, creator, height, width, isWaterColor, isFramed);
    }
}
