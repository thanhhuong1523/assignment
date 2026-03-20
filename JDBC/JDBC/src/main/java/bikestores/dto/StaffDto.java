package bikestores.dto;

public class StaffDto {
    private String name;
    private String role;
    private String email;
    private String phone;
    private int storeID;

    public StaffDto() {}

    public StaffDto(String name, String role, String email, String phone, int storeID) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.storeID = storeID;
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
}
