package sales.entities;

public class Order {
    private int id;
    private int customerID;
    private int employeeID;
    private String orderDate;

    public Order() {}

    public Order(int id, int customerID, int employeeID, String orderDate) {
        this.id = id;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-5s | %-5s | %-10s",
                id, customerID, employeeID, orderDate);
    }
}
