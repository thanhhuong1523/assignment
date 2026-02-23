package Class_and_Object.Lab02;

public class InputValidator {
    public static boolean isValidID(int id) {
        return id > 0;
    }

    public static boolean isUniqueID(int id, Student[] listStudents, int countStudents) {
        for(int i = 0; i < countStudents; i++) {
            if(listStudents[i] != null && listStudents[i].getId() == id) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidAge(int age) {
        return age >= 18;
    }

    public static boolean isValidGender(String gender) {
        if(gender == null) {
            return false;
        }
        gender = gender.trim();
        return gender.equalsIgnoreCase("male") ||
                gender.equalsIgnoreCase("female");
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }
}
