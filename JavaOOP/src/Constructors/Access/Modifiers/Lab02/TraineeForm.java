package Constructors.Access.Modifiers.Lab02;

import java.util.Scanner;

/**
 * Responsible only for reading and building Trainee from user input (via Scanner)
 * Uniqueness of id is checked by TrainingManagement, but this form exposes getId() for that
 */

public class TraineeForm {
    private final Scanner scanner;

    public TraineeForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getId() {
        while(true) {
            System.out.print("Enter trainee ID: ");
            String id = scanner.nextLine().trim();

            if(!id.isEmpty()) return id;
            System.out.println("Empty ID. Try again!");
        }
    }

    private String readNonEmpty() {
        while(true) {
            System.out.print("Enter name: ");
            String value = scanner.nextLine().trim();
            if(!value.isEmpty()) return value;
            System.out.println("Cannot be empty!");
        }
    }

    private String readGender() {
        while(true) {
            System.out.print("Enter gender(male/ female): ");
            String gender = scanner.nextLine().trim().toLowerCase();

            if(gender.equals("male") || gender.equals("female")) return gender;

            System.out.println("Invalid gender!");
        }
    }

    private byte readAge() {
        while (true) {
            try {
                System.out.print("Enter age: ");
                byte age = Byte.parseByte(scanner.nextLine().trim());

                if(age >= 6) return age;

                System.out.println("Invalid age!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again!");
            }
        }
    }

    public Trainee getTrainee() {
        String name = readNonEmpty();
        String gender = readGender();
        byte age = readAge();

        return new Trainee("TEMP", name, gender, age);
    }
}