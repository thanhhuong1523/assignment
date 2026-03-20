package bikestores.models;

public class Store {
    private int storeID;
    private String name;
    private String address;
    private String phone;

    public Store() {}

    public Store(int storeID, String name, String address, String phone) {
        this.storeID = storeID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-15s | %-15s | %-15s",
                storeID, name, address, phone);
    }
}
