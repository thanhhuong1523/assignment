package bikestores.models;

import java.util.Date;

public class Order {
    private int orderID;
    private int customerID;
    private Date orderDate;
    private int discountID;
    private String status;
    private double totalAmount;

    public Order() {}

    public Order(int orderID, int customerID, Date orderDate, int discountID, String status, double totalAmount) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.discountID = discountID;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s",
                orderID, customerID, orderDate, discountID, status, totalAmount);
    }
}