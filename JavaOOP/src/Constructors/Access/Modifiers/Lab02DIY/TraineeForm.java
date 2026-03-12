package Constructors.Access.Modifiers.Lab02DIY;

import java.util.Scanner;

public class TraineeForm {
    private Scanner scanner;

    public TraineeForm() {}

    public TraineeForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getID() {
        String id;
        while(true) {
            System.out.print("Enter ID: ");
            id = scanner.nextLine();
            if(id == null || id.trim().isEmpty()) {
                System.out.println("Empty ID!");
            } else break;
        }
        return id.trim();
    }

    public Trainee getTrainee() {
        String name = getName();
        String gender = getGender();
        byte age = getAge();

        return new Trainee("temp", name, gender, age);
    }

    public String getName() {
        String name;
        while(true) {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if(name == null || name.trim().isEmpty()) {
                System.out.println("Empty name!");
            } else break;
        }
        return name.trim();
    }

    private String getGender() {
        String gender;
        while(true) {
            System.out.print("Enter gender: ");
            gender = scanner.nextLine();
            if(gender == null || gender.trim().isEmpty()) {
                System.out.println("Empty gender!");
            } else if(!gender.trim().equalsIgnoreCase("male") &&
                    !gender.trim().equalsIgnoreCase("female")) {
                System.out.println("Gender must be \"male\" or \"female\"!");
            } else break;
        }
        return gender.trim().toLowerCase();
    }

    private byte getAge() {
        byte age;
        String line;
        while(true) {
            try {
                System.out.print("Enter age: ");
                line = scanner.nextLine();
                if(line == null || line.trim().isEmpty()) {
                    System.out.println("Empty age!");
                    continue;
                }
                age = Byte.parseByte(line.trim());
                if(age < 6) {
                    System.out.println("Invalid age!");
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
            }
        }
        return age;
    }
}
