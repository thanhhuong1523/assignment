package Collections.utils;

import java.util.Scanner;

public class ScannerUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readNonEmpty(String prompt) {
        String line;
        while (true) {
            System.out.print(prompt);
            line = scanner.nextLine();

            if(line == null || line.trim().isEmpty()) {
                System.out.println("Empty value!");
            } else break;
        }
        return line.trim();
    }

    public static boolean readBoolean(String prompt) {
        String line;
        while (true) {
            System.out.print(prompt + "(true/false): ");
            line = scanner.nextLine().trim().toLowerCase();

            if(!line.equals("true") && !line.equals("false")) {
                System.out.println("Invalid value");
            } else break;
        }
        return Boolean.parseBoolean(line);
    }

    public static short readPositiveShort(String prompt) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();

            try {
                short shortValue = Short.parseShort(value);

                if(shortValue <= 0) {
                    System.out.println("Please enter positive value!");
                } else return shortValue;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
            }
        }
    }

    public static int readMenuChoice() {
        while (true) {
            System.out.print("Enter your choice: ");
            String value = scanner.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e){
                System.out.print("Enter your choice: ");
            }
        }
    }
}
