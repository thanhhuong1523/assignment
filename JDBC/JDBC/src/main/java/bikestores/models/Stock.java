package bikestores.models;

public class Stock {
    private int stockID;
    private int storeID;
    private int productID;
    private int quantity;

    public Stock() {}

    public Stock(int stockID, int storeID, int productID, int quantity) {
        this.stockID = stockID;
        this.storeID = storeID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %-10s | %-10s",
                stockID, storeID, productID, quantity);
    }
}
