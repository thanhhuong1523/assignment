package sales.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ScannerUtils {
    public static String readNonEmpty(Scanner scanner, String prompt) {
        String line;

        while (true) {
            System.out.print(prompt);
            line = scanner.nextLine();

            if(line == null || line.trim().isEmpty()) {
                System.out.println("Empty value!");
            } else return line.trim();
        }
    }

    public static int readPositiveInt(Scanner scanner, String prompt) {
        String line;
        while (true) {
            line = readNonEmpty(scanner, prompt);

            try {
                int num = Integer.parseInt(line);

                if(num <= 0) {
                    System.out.println("Please enter a positive number!");
                } else return num;

            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
            }
        }
    }

    public static String readDate(Scanner scanner, String prompt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            String line = readNonEmpty(scanner, prompt);

            try {
                LocalDate.parse(line, dtf);
                return line;
            } catch (Exception e) {
                System.out.println("Invalid date format!");
            }
        }
    }

    public static boolean quitMenu(Scanner scanner) {
        System.out.print("Are you sure to exit? Please enter 1 to confirm! (1/0): ");
        String opt = scanner.nextLine();

        return opt != null && opt.trim().equals("1");
    }
}
