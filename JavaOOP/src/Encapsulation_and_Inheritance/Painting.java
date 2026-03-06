package Encapsulation_and_Inheritance;

import java.util.Scanner;

public class Painting extends Item {
    private int height;
    private int width;
    private boolean isWaterColor;
    private boolean isFramed;

    public Painting() {}

    public Painting(String id, int value, String creator, int height, int width, boolean isWaterColor, boolean isFramed) {
        super(id, value, creator);
        this.height = height;
        this.width = width;
        this.isWaterColor = isWaterColor;
        this.isFramed = isFramed;
    }

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

    public void input(Scanner scanner) {

    }

    private void inputNumber(Scanner scanner) {
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

    private void inputWidth(Scanner scanner) {
        String line;
        try {
            while (true) {
                line = scanner.nextLine();
                if(line == null || line.trim().isEmpty()) {
                    System.out.println("Empty width!");
                } else {
                    width = Integer.parseInt(line.trim());
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
    }

    private void inputIsWaterColor(Scanner scanner) {
        String line;
        while (true) {
            line = scanner.nextLine();
            if(line == null || line.trim().isEmpty()) {
                System.out.println("Empty isWaterColor!");
            } else if(!line.trim().equalsIgnoreCase("true") && !line.trim().equalsIgnoreCase("false")) {
                System.out.println("Invalid value");
            } else {
                isWaterColor = Boolean.parseBoolean(line.trim().toLowerCase());
                break;
            }
        }
    }

    private void inputIsFramed(Scanner scanner) {
        String line;
        while (true) {
            line = scanner.nextLine();
            if(line == null || line.trim().isEmpty()) {
                System.out.println("Empty isFramed!");
            } else if(!line.trim().equalsIgnoreCase("true") &&
                    !line.trim().equalsIgnoreCase("false")) {
                System.out.println("Invalid value");
            } else {
                isFramed = Boolean.parseBoolean(line.trim().toLowerCase());
                break;
            }
        }
    }
}
