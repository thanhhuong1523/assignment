package Collections.main;

import Collections.entities.Course;
import Collections.utils.Constants;
import Collections.utils.ScannerUtil;
import Collections.utils.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class CourseManagement {
    private final ArrayList<Course> courses = new ArrayList<>();

    private void runMenu(){
        while (true) {
            showMenu();
            int choice = ScannerUtil.readMenuChoice();

            switch (choice) {
                case Constants.MENU_CREATE -> createCourse();
                case Constants.MENU_SEARCH -> searchCourse();
                case Constants.MENU_DISPLAY_BY_FLAG -> displayByFlag();
                case Constants.MENU_QUIT -> {
                    System.out.println("Are you sure to exit? Please enter 1 to confirm! (1/0)");
                    int opt = ScannerUtil.readMenuChoice();
                    if(opt == 1) {
                        System.out.println("Goodbye");
                        return;
                    }
                }
                default -> System.out.println("Invalid choice!");
            }
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
        ArrayList<Course> result = new ArrayList<>();

        switch (choice) {
            case 1:
                String code = ScannerUtil.readNonEmpty("Enter code: ");
                if(!Validator.isDuplicateCode(code, courses)) {
                    System.out.println("Code not found!");
                    break;
                }

                for(Course c : courses) {
                    if(c.getCode().equalsIgnoreCase(code)) {
                        result.add(c);
                    }
                }
                break;
            case 2:
                String name = ScannerUtil.readNonEmpty("Enter name: ");
                for(Course c : courses) {
                    if(c.getName().toLowerCase(Locale.ROOT)
                            .contains(name.toLowerCase(Locale.ROOT))) {
                        result.add(c);
                    }
                }
                result.sort(Comparator.comparing(Course::getName));
                break;
            case 3:
                boolean keyword = ScannerUtil.readBoolean("Enter status ");
                for(Course c : courses) {
                    if(c.isStatus() == keyword) {
                        result.add(c);
                    }
                }
                break;
            case 4:
                short duration = ScannerUtil.readPositiveShort("Enter number: ");
                for(Course c : courses) {
                    if(c.getDuration() == duration) {
                        result.add(c);
                    }
                }
                break;
            case 5:
                String flag = readValidFlag();
                for(Course c : courses) {
                    if(c.getFlag().equalsIgnoreCase(flag)) {
                        result.add(c);
                    }
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }

        printCourse(result);
    }

    private void printCourse(ArrayList<Course> listCourse) {
        if(listCourse.isEmpty()) {
            System.out.println("No courses");
            return;
        }

        System.out.println(Constants.TABLE_HEADER);
        listCourse.forEach(System.out::println);
    }

    private void displayByFlag() {
        String flag = readValidFlag();
        ArrayList<Course> listCourse = new ArrayList<>();

        for(Course c : courses) {
            if(c.getFlag().equalsIgnoreCase(flag)) {
                listCourse.add(c);
            }
        }

        printCourse(listCourse);
    }

    public static void main(String[] args) {
        new CourseManagement().runMenu();
    }
}
