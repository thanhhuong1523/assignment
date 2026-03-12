package Collections.main;

import Collections.entities.Course;
import Collections.utils.Constants;
import Collections.utils.ScannerUtil;
import Collections.utils.Validator;

import java.util.ArrayList;
import java.util.Locale;

public class CourseManagement {
    private final ArrayList<Course> courses = new ArrayList<>();

    private void runMenu(){
        while (true) {

        }
    }

    private void showMenu() {
        System.out.println("------ Course Management ------");
        System.out.println("1. Create a course");
        System.out.println("2. Search courses by attribute");
        System.out.println("3. Display courses by flag");
        System.out.println("0. Exit");
    }

    private void createCourse() {
        String code = readValidCode();
        String name = ScannerUtil.readNonEmpty("Enter name: ");
        boolean status = ScannerUtil.readBoolean("Enter status: ");
        short duration = ScannerUtil.readPositiveShort("Enter duration (positive): ");
        String flag = readValidFlag();

        courses.add(new Course(code, name, status, duration, flag));
        System.out.println("Create new course successfully!");
    }

    private String readValidCode() {
        while (true) {
            String code = ScannerUtil.readNonEmpty("Enter course code (RAxxx): ").toUpperCase(Locale.ROOT);

            if(!Validator.validateCode(code)) {
                System.out.println("Invalid code pattern!");
                continue;
            }

            if(Validator.isDuplicateCode(code, courses)) {
                System.out.println("Duplicate code!");
                continue;
            }
            return code;
        }
    }

    private String readValidFlag() {
        while (true) {
            String flag = ScannerUtil.readNonEmpty("Enter flag (optional/ prerequisite/ N/A): ");

            if(Validator.validateFlag(flag)) {
                return flag.equalsIgnoreCase("n/a") ? "N/A"
                        : flag.toLowerCase(Locale.ROOT);
            }
            System.out.println("Invalid flag");
        }
    }

    private void searchCourse() {
        System.out.println("Which one do you want to search?");
        System.out.println("1. Code");
        System.out.println("2. Name");
        System.out.println("3. Status");
        System.out.println("4. Duration");
        System.out.println("5. Flag");
        System.out.print("Enter your choice: ");

        int choice = ScannerUtil.readMenuChoice();

        switch (choice) {
            case 1:
                String code = ScannerUtil.readNonEmpty("Enter code: ");
                if(!Validator.isDuplicateCode(code, courses)) {
                    System.out.println("Code not found!");
                    break;
                }
                ArrayList<Course> result = new ArrayList<>();

                System.out.println(Constants.TABLE_HEADER);
                for(Course c : courses) {
                    if(c.getCode().equalsIgnoreCase(code)) {
                        result.add(c);
                        System.out.println(c);
                    }
                }

        }
    }

    public static void main(String[] args) {

    }
}
