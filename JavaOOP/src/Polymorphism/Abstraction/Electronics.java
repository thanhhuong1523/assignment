package Polymorphism.Abstraction;

public class Electronics extends Product {
    private String brand;

    public Electronics() {}

    public Electronics(int id, String name, float price, String brand) {
        super(id, name, price);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String header() {
        return String.format("%-5s | %-10s | %-8s | %-10s",
                "ID", "Name", "Price", "Brand");
    }

    @Override
    public String getType() {
        return "Electronics";
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-10s | %-8.2f | %-10s",
                            id, name, price, brand);
    }
}
