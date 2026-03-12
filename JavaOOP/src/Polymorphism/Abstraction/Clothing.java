package Polymorphism.Abstraction;

public class Clothing  extends Product{
    private String size;

    public Clothing() {}

    public Clothing(int id, String name, float price, String size) {
        super(id, name, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String header() {
        return String.format("%-5s | %-10s | %-8s | %-5s",
                "ID", "Name", "Price", "Size");
    }

    @Override
    public String getType() {
        return "Clothing";
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-10s | %-8.2f | %-5s",
                            id, name, price, size);
    }
}
