package bikestores.models;

public class Product {
    private int productID;
    private String name;
    private int categoryID;
    private int brandID;
    private double price;
    private String description;

    public Product() {}

    public Product(int productID, String name, int categoryID, int brandID, double price, String description) {
        this.productID = productID;
        this.name = name;
        this.categoryID = categoryID;
        this.brandID = brandID;
        this.price = price;
        this.description = description;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-15s | %-10s | %-10s | %-10s | %-20s",
                productID, name, categoryID, brandID, price, description);
    }
}