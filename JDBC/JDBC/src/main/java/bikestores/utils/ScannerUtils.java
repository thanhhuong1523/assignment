package bikestores.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class ScannerUtils {
    public static String readNullableString(Scanner scanner, String prompt) {
        System.out.print(prompt);

        String line = scanner.nextLine();
        if (line == null || line.trim().isEmpty()) return null;

        return line.trim();
    }

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

    public static int readPositiveInt(Scanner scanner, String prompt, boolean isNullable) {
        String line;
        while (true) {
            line = isNullable ? readNullableString(scanner, prompt) : readNonEmpty(scanner, prompt);
            if(line == null) return -1;

            if(!ValidatorUtils.isNumber(line)) {
                System.out.println("Invalid number format!");
                continue;
            }

            int num = Integer.parseInt(line);

            if(num <= 0) {
                System.out.println("Please enter a positive number!");
            } else return num;
        }
    }

    public static String readDate(Scanner scanner, String prompt, boolean isNullable) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String line;

        while (true) {
            line = isNullable ? readNullableString(scanner, prompt): readNonEmpty(scanner, prompt);
            if(line == null) return null;

            try {
                LocalDate.parse(line, dtf);
                return line;
            } catch (Exception e) {
                System.out.println("Invalid date format!");
            }
        }
    }

    public static String readValidGender(Scanner scanner, String prompt, boolean isNullable) {
        String line;
        while (true) {
            line = isNullable ? readNullableString(scanner, prompt) : readNonEmpty(scanner, prompt);
            if(line == null) return null;

            line = line.toLowerCase(Locale.ROOT);

            if(!ValidatorUtils.isValidGender(line)) {
                System.out.println("Invalid gender");
            } else return line;
        }
    }

    public static boolean quitMenu(Scanner scanner) {
        System.out.print("Are you sure to exit? Please enter 1 to confirm!: ");
        String opt = scanner.nextLine();

        return opt != null && opt.trim().equals("1");
    }
}
