package bikestores.models;

public class Customer {
    private int customerID;
    private String name;
    private String gender;
    private String phone;
    private String email;

    public Customer() {}

    public Customer(int customerID, String name, String gender, String phone, String email) {
        this.customerID = customerID;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-20s | %-10s | %-15s | %-15s",
                customerID, name, gender, phone, email);
    }
}