package bikestores.utils;

public class ValidatorUtils {
    public static boolean isValidEmail (String email) {
        String regex = "^[\\w._%+-]+@gmail\\.com$";
        return email.matches(regex);
    }

    public static boolean isValidPhone (String phone) {
        String regex = "^(0|\\+84)\\d{9}$";
        return phone.matches(regex);
    }

    public static boolean isNumber(String value) {
        return value.matches("\\d+");
    }

    public static boolean isValidGender(String gender) {
        return gender.equals("male") || gender.equals("female") || gender.equals("other");
    }
}
