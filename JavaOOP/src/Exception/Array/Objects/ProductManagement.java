package Exception.Array.Objects;

import java.util.Scanner;

public class ProductManagement {
    private final Product[] products;
    private int numOfProducts = 0;
    private final int MAX = 10;

    public ProductManagement() {
        products = new Product[MAX];
    }

    public void addProduct(Product product) throws IllegalArgumentException {
        if(numOfProducts >= MAX) {
            throw new IllegalArgumentException("Full products!");
        }

        for(int i = 0; i < numOfProducts; i++) {
            if(products[i].getProductID() == product.getProductID()) {
                throw new IllegalArgumentException("ProductID has been already existed!");
            }
        }

        products[numOfProducts++] = product;
        System.out.println("Add new product successfully");
    }

    // get product by ID entered
    public Product getProductByID(int productID) throws ProductNotFoundException {
        for(int i = 0; i < numOfProducts; i++) {
            if(products[i].getProductID() == productID) {
                return products[i];
            }
        }

        throw new ProductNotFoundException("Product not found!");
    }

    // update quantity
    public void updateProductQuantity(int productID, int newQuantity)
            throws ProductNotFoundException, IllegalArgumentException {
        if(newQuantity < 0) {
            throw new IllegalArgumentException("Negative quantity!");
        }

        Product product = getProductByID(productID);
        product.setQuantityInStock(newQuantity);
        System.out.println("Update product's quantity successfully!");
    }
}
