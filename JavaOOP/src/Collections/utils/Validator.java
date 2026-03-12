package Collections.utils;

import Collections.entities.Course;

import java.util.ArrayList;

public final class Validator {
    public static boolean validateCode(String code) {
        return code != null && code.matches(Constants.COURSE_CODE_REGEX);
    }

    public static boolean isDuplicateCode(String code, ArrayList<Course> courses) {
        for(Course course : courses) {
            if(course.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateFlag(String flag) {
        if(flag == null) return false;

        String normalizedFlag = flag.trim();
        for(int i = 0; i < Constants.ALLOWED_FLAGS.length; i++) {
            if(Constants.ALLOWED_FLAGS[i].equalsIgnoreCase(flag)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateDuration(short duration) {
        return duration > 0;
    }
}
