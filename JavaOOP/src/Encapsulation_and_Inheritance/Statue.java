package Encapsulation_and_Inheritance;

import javax.security.sasl.SaslClient;
import java.util.Scanner;

public class Statue extends Item {
    private int weight;
    private String color;

    public Statue() {}

    public Statue(String id, int value, String creator, int weight, String color) {
        super(id, value, creator);
        this.weight = weight;
        this.color = color;
    }

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

    public void input(Scanner scanner) {
        inputWeight(scanner);
        inputColor(scanner);
    }

    private void inputWeight(Scanner scanner) {
        try {
            String line;
            while(true) {
                line = scanner.nextLine();
                if(line == null || line.trim().isEmpty()) {
                    System.out.println("Empty weight!");
                } else {
                    weight = Integer.parseInt(line.trim());
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
    }

    private void inputColor(Scanner scanner) {
        while (true) {
            color = scanner.nextLine();
            if(color == null || color.trim().isEmpty()) {
                System.out.println("Invalid color!");
            } else {
                color = color.trim();
                break;
            }
        }
    }

    public String toString() {
        return String.format("%-8s | %5d | %-10s | %5d | %-10s", id, value, creator, weight, color);
    }
}
