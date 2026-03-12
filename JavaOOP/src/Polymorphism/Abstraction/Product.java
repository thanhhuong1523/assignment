package Polymorphism.Abstraction;

public abstract class Product {
    protected int id;
    protected String name;
    protected float price;

    public Product() {}

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    abstract public String header();

    abstract public String getType();

    @Override
    public String toString() {
        return String.format("%-5d | %-10s | %-8.2f", id, name, price);
    }
}
