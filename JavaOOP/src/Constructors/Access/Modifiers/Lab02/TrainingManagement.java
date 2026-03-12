package Constructors.Access.Modifiers.Lab02;

import java.util.Arrays;
import java.util.Scanner;

public class TrainingManagement {
    private final TraineeForm traineeForm;
    private final Scanner scanner;
    private final Trainee[] listTrainees = new Trainee[100];
    private byte countTrainees = 0;

    public TrainingManagement(){
        this.scanner = new Scanner(System.in);
        this.traineeForm = new TraineeForm(scanner);
    }

    private int getIndexByID(String id) {
        for(int i = 0; i < countTrainees; i++) {
            if(listTrainees[i].getId().equalsIgnoreCase(id)) return i;
        }
        return -1;
    }

    private void addTrainee() {
        if(countTrainees >= listTrainees.length) {
            System.out.println("Full trainees!");
            return;
        }
        String id;
        while (true) {
            id = traineeForm.getId();
            if(getIndexByID(id) == -1) break;
            System.out.println("Duplicate ID");
        }

        Trainee trainee = traineeForm.getTrainee();
        trainee.setId(id);
        listTrainees[countTrainees++] = trainee;

        System.out.println("Create successfully");
    }

    private void displayAllTrainees() {
        if(countTrainees == 0) {
            System.out.println("No trainees yet");
            return;
        }

        System.out.println(header());
        for(int i = 0; i < countTrainees; i++) {
            System.out.println(listTrainees[i]);
        }
    }

    private String header() {
        return String.format("%-8s | %-18s | %-6s | %3s", "ID", "NAME", "GENDER", "AGE");
    }

    private Trainee findTraineeByID(String id) {
        int index = getIndexByID(id);
        return index == -1 ? null : listTrainees[index];
    }

    private Trainee[] findTraineeByName(String name) {
        String key = name.toLowerCase();
        Trainee[] temp = new Trainee[countTrainees];
        int k = 0;

        for(int i = 0; i < countTrainees; i++) {
            if(listTrainees[i].getName().toLowerCase().contains(key)) {
                temp[k++] = listTrainees[i];
            }
        }
        return Arrays.copyOf(temp, k);
    }

    private void updateTrainee(String id, Trainee newTrainee) {
        int index = getIndexByID(id);
        if(index == -1) {
            System.out.println("ID not found");
            return;
        }
        listTrainees[index].setName(newTrainee.getName());
        listTrainees[index].setGender(newTrainee.getGender());
        listTrainees[index].setAge(newTrainee.getAge());

        System.out.println("Update successfully!");
    }

    public static void main(String[] args) {
        new TrainingManagement().runMenu();
    }

    private void runMenu() {
        while (true) {
            System.out.println("------ Trainee Management ------");
            System.out.println("1. Add trainee");
            System.out.println("2. Display all trainees");
            System.out.println("3. Find trainee by ID");
            System.out.println("4. Find trainee by name");
            System.out.println("5. Update trainee by ID");
            System.out.println("6. Exit");
            System.out.print("Your choice is: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addTrainee();
                case "2" -> displayAllTrainees();
                case "3" -> {
                    System.out.print("Enter ID to find: ");
                    String id = scanner.nextLine().trim();

                    Trainee trainee = findTraineeByID(id);
                    System.out.println(trainee != null ? header() + "\n" + trainee : "Not found");
                }

                case "4" -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().trim();

                    Trainee[] result = findTraineeByName(name);
                    if(result.length == 0) {
                        System.out.println("No match");
                    } else {
                        System.out.println(header());
                        for(Trainee t : result) {
                            System.out.println(t);
                        }
                    }
                }

                case "5" -> {
                    System.out.print("Enter ID to update: ");
                    String id = scanner.nextLine().trim();

                    Trainee newTrainee = traineeForm.getTrainee();
                    updateTrainee(id, newTrainee);
                }

                case "6" -> {
                    System.out.println("Byeeee!");
                    return;
                }

                default -> System.out.println("Invalid choice");
            }
        }
    }
}
