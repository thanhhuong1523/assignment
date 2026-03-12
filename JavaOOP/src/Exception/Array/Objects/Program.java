package Exception.Array.Objects;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        ProductManagement pm = new ProductManagement();
        Scanner scanner = new Scanner(System.in);

        int choice;

        while (true) {
            System.out.println("------ Product Management Menu ------");
            System.out.println("1. Add product");
            System.out.println("2. Find product by ID");
            System.out.println("3. Update product quantity");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        try {
                            System.out.print("Enter product ID: ");
                            int productID = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter product name: ");
                            String name = scanner.nextLine();

                            System.out.print("Enter product price: ");
                            double price = Double.parseDouble(scanner.nextLine());

                            System.out.print("Enter quantity in stock: ");
                            int quantity = Integer.parseInt(scanner.nextLine());

                            Product product = new Product(productID, name, price, quantity);
                            pm.addProduct(product);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 2:
                        try {
                            System.out.print("Enter product ID: ");
                            int productID = Integer.parseInt(scanner.nextLine());

                            Product product = pm.getProductByID(productID);
                            product.displayProductInfo();
                        } catch (ProductNotFoundException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 3:
                        try {
                            System.out.print("Enter product ID: ");
                            int productID = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter new quantity: ");
                            int quantity = Integer.parseInt(scanner.nextLine());

                            pm.updateProductQuantity(productID, quantity);
                        } catch (ProductNotFoundException | IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 0:
                        System.out.print("Are you sure to exit? Please enter 1 to confirm! (1/0): ");
                        int opt = Integer.parseInt(scanner.nextLine());

                        if(opt == 1) {
                            System.out.println("Goodbye!");
                            return;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
            }
        }
    }
}
