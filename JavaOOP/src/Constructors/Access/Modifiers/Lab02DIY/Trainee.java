package Constructors.Access.Modifiers.Lab02DIY;

public class Trainee {
    private String id;
    private String name;
    private String gender;
    private byte age;

    public Trainee() {}

    public Trainee(String id, String name, String gender, byte age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id == null || id.trim().isEmpty()) {
            System.out.println("Empty ID!");
            return;
        }
        this.id = id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            System.out.println("Empty name!");
            return;
        }
        this.name = name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender == null || gender.trim().isEmpty()) {
            System.out.println("Empty gender");
            return;
        }
        String normalizedGender = gender.trim().toLowerCase();
        if(!normalizedGender.equals("male") && !normalizedGender.equals("female")) {
            System.out.println("Invalid gender");
            return;
        }
        this.gender = normalizedGender;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        if(age < 6) {
            System.out.println("Invalid age");
            return;
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-10s | %-5s | %3d", id, name, gender, age);
    }
}
