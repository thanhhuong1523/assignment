package Constructors.Access.Modifiers.Lab02DIY;

import java.util.Arrays;
import java.util.Scanner;

public class TraineeManagement {
    private final TraineeForm traineeForm;
    private final Scanner scanner;
    private final Trainee[] listTrainees = new Trainee[100];
    private byte countTrainees = 0;

    public TraineeManagement() {
        this.scanner = new Scanner(System.in);
        this.traineeForm = new TraineeForm(scanner);
    }

    public static void main(String[] args) {
        new TraineeManagement().runMenu();
    }

    private void runMenu() {
        while(true) {
            System.out.println("\n ----- Trainee Management -----");
            System.out.println("1. Add trainee");
            System.out.println("2. Display all trainees");
            System.out.println("3. Find trainee by ID");
            System.out.println("4. Find trainee by name");
            System.out.println("5. Update trainee by ID");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addTrainee();
                    break;
                case "2":
                    displayAllTrainees();
                    break;
                case "3":
                    getTraineeByID();
                    break;
                case "4":
                    Trainee[] tmp = getTraineeByName();
                    if(tmp.length == 0) {
                        System.out.println("Trainee not found");
                    } else {
                        for(Trainee trainee : tmp) {
                            System.out.println(header());
                            System.out.println(trainee);
                        }
                    }
                    break;
                case "5":
                    updateTraineeByID();
                    break;
                case "6":
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private void addTrainee() {
        if(countTrainees >= listTrainees.length) {
            System.out.println("Full trainees");
            return;
        }
        String id;
        while(true) {
            id = traineeForm.getID();
            if(getIndexByID(id) != -1) {
                System.out.println("ID has already existed!");
            } else break;
        }
        Trainee trainee = traineeForm.getTrainee();
        trainee.setId(id);
        listTrainees[countTrainees++] = trainee;
        System.out.println("Create new trainee successfully!");
    }

    private int getIndexByID(String id) {
        for(int i = 0; i < countTrainees; i++) {
            if(listTrainees[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    private void displayAllTrainees() {
        if(countTrainees == 0) {
            System.out.println("No trainees");
            return;
        }
        System.out.println(header());

        for(int i = 0; i < countTrainees; i++) {
            System.out.println(listTrainees[i]);
        }
    }

    private String header() {
        return String.format("%-5s | %-10s | %-5s | %3s", "ID", "Name", "Gender", "Age");
    }

    private void getTraineeByID() {
        String id = traineeForm.getID();
        int index = getIndexByID(id);
        if(index == -1) {
            System.out.println("ID not found!");
            return;
        }
        System.out.println(header());
        System.out.println(listTrainees[index]);
    }

    private Trainee[] getTraineeByName() {
        String name = traineeForm.getName();
        Trainee[] tmp = new Trainee[countTrainees];
        int k = 0;

        for(int i = 0; i < countTrainees; i++) {
            if(listTrainees[i].getName().toLowerCase().contains(name)) {
                tmp[k++] = listTrainees[i];
            }
        }
        return Arrays.copyOf(tmp, k);
    }

    private void updateTraineeByID() {
        String id = traineeForm.getID();
        int index = getIndexByID(id);

        if(index == -1) {
            System.out.println("ID not found!");
            return;
        }
        Trainee trainee = traineeForm.getTrainee();

        listTrainees[index].setName(trainee.getName());
        listTrainees[index].setGender(trainee.getGender());
        listTrainees[index].setAge(trainee.getAge());

        System.out.println("Update trainee successfully!");
    }

    private void exit() {
        System.out.print("Are you sure to exit? Please enter 1 to confirm! (1/0): ");
        String choice = scanner.nextLine();
        if(choice == null) return;
        choice = choice.trim();
        if(choice.equals("1")) {
            System.exit(0);
        }
    }
}
