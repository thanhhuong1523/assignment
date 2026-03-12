package Class.Object.Lab02;

import java.util.Scanner;

public class StudentManagement {
    private static final int MAX = 100;
    private static final Student[] listStudents = new Student[MAX];
    private static int countStudents = 0;

    private static final Scanner scanner = new Scanner(System.in);

    public static Student createStudent() {
        int id;
        do {
            System.out.print("Enter ID: ");
            id = Integer.parseInt(scanner.nextLine());

            if(!InputValidator.isValidID(id) || !InputValidator.isUniqueID(id, listStudents, countStudents)) {
                System.out.println("Invalid ID");
            } else break;
        } while(true);

        String name;
        do {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if(!InputValidator.isValidName(name)) {
                System.out.println("Invalid name");
            } else break;
        } while(true);


        int age;
        do {
            System.out.print("Enter age: ");
            age = Integer.parseInt(scanner.nextLine());
            if(!InputValidator.isValidAge(age)) {
                System.out.println("Invalid age");
            } else break;
        } while (true);

        System.out.print("Enter address: ");
        String address = scanner.nextLine();


        String gender;
        do {
            System.out.print("Enter gender: ");
            gender = scanner.nextLine();
            if(!InputValidator.isValidGender(gender)){
                System.out.println("Invalid gender");
            } else break;
        } while(true);

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        return new Student(id, name, age, address, gender, email);
    }

    public static void addStudent() {
        if(countStudents >= MAX) {
            System.out.println("Full students!");
            return;
        }
        listStudents[countStudents++] = createStudent();
        System.out.println("Create a new student successfully!");
    }

    public static void displayAllStudents() {
        if(countStudents == 0) {
            System.out.println("No students");
            return;
        }
        for(int i = 0; i < countStudents; i++) {
            System.out.println(listStudents[i]);
        }
    }

    public static int findStudentIndexByID() {
        int id;

        do {
            System.out.print("Enter ID to find: ");
            id = Integer.parseInt(scanner.nextLine());

            if(!InputValidator.isValidID(id)) {
                System.out.println("Invalid ID");
            } else break;
        } while (true);

        for(int i = 0; i < countStudents; i++) {
            if(listStudents[i].getId() == id) {
                return i;
            }
        }

        return -1;
    }

    public static void findStudentByID() {
        int index = findStudentIndexByID();

        if(index == -1) {
            System.out.println("Student not found!");
        } else {
            System.out.println(listStudents[index]);
        }
    }

    public static void updateStudentByID() {
        int index = findStudentIndexByID();

        if(index == -1) {
            System.out.println("Student not found!");
            return;
        }

        Student student = listStudents[index];
        String name;
        do {
            System.out.print("Enter new name: ");
            name = scanner.nextLine();
            if(!InputValidator.isValidName(name)) {
                System.out.println("Invalid name");
            } else break;
        } while(true);

        int age;
        do {
            System.out.print("Enter new age: ");
            age = Integer.parseInt(scanner.nextLine());

            if(!InputValidator.isValidAge(age)) {
                System.out.println("Invalid age");
            } else break;
        } while (true);

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        String gender;
        do {
            System.out.print("Enter new gender: ");
            gender = scanner.nextLine();
            if(!InputValidator.isValidGender(gender)){
                System.out.println("Invalid gender");
            } else break;
        } while(true);

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        student.setName(name);
        student.setAge(age);
        student.setAddress(address);
        student.setGender(gender);
        student.setEmail(email);

        System.out.println("Update student successfully!");
    }

    public static boolean quit() {
        System.out.print("Are you sure to exit? Please enter 1 to confirm! (1/0): ");
        int choice = scanner.nextInt();

        return choice == 1;
    }
}
