package sales.entities;

public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String birthday;
    private int supervisor;

    public Employee() {}

    public Employee(int id, String lastName, String firstName, String birthday, int supervisor) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.supervisor = supervisor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-8s | %-8s | %-10s | %-5s",
                id, lastName, firstName, birthday, supervisor);
    }
}
