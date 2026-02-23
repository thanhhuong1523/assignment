package Class_and_Object.Lab02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while(true) {
            System.out.println("----- Student Management -----");
            System.out.println("1. Create a student.");
            System.out.println("2. Display all students");
            System.out.println("3. Find a student by ID");
            System.out.println("4. Update a student by ID");
            System.out.println("5. Quit");

            System.out.print("Your choice is: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    StudentManagement.addStudent();
                    break;
                case 2:
                    StudentManagement.displayAllStudents();
                    break;
                case 3:
                    StudentManagement.findStudentByID();
                    break;
                case 4:
                    StudentManagement.updateStudentByID();
                    break;
                case 5:
                    if(StudentManagement.quit()) {
                        System.exit(0);
                    };
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
