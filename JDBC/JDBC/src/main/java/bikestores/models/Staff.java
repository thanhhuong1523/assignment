package bikestores.models;

public class Staff {
    private int staffID;
    private String name;
    private String role;
    private String email;
    private String phone;
    private int storeID;
    private String storeName;

    public Staff() {}

    public Staff(int staffID, String name, String role, String email, String phone, int storeID, String storeName) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.storeID = storeID;
        this.storeName = storeName;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-15s | %-10s | %-20s | %-15s | %-10s | %-15s",
                staffID, name, role, email, phone, storeID, storeName);
    }
}
