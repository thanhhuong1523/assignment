package Collections.entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String code;
    private String name;
    private boolean status;
    private short duration;
    private String flag;

    public Course() {}

    public Course(String code, String name, boolean status, short duration, String flag) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-20s | %-6s | %-8d | %-12s",
                code, name, status, duration, flag);
    }
}
