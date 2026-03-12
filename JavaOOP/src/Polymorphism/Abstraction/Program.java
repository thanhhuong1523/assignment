package Polymorphism.Abstraction;

import java.util.Scanner;

public class Program {
    private final Product[] products;
    private byte numOfProduct = 0;
    private final byte MAX = 100;
    private static final Scanner scanner = new Scanner(System.in);

    // constructor to initial product list
    public Program() {
        products = new Product[MAX];
    }

    // Add a new product to product list
    public void addProduct(Product product) {
        if(numOfProduct == MAX || product == null) {
            System.out.println("Cannot add product to the list!");
        } else {
            products[numOfProduct++] = product;
            System.out.println("Add this product successfully!");
        }
    }

    // Show all products by type
    public void displayProducts() {
        if(numOfProduct == 0) {
            System.out.println("No products");
        } else {
            displayProductOfType("Electronics");
            displayProductOfType("Clothing");
        }
    }

    // return the product in the list by ID entered
    public Product findProduct(int id) {
        int index = getIndexByID(id);
        return index != -1 ? products[index] : null;
    }

    // return the index of product in the list by ID entered
    private int getIndexByID(int id) {
        if(numOfProduct == 0) return -1;

        for(int i = 0; i < numOfProduct; i++) {
            if(products[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    // show all products of the type entered
    public void displayProductOfType(String type) {
        boolean flag = false;

        for(int i = 0; i < numOfProduct; i++) {
            if(products[i].getType().equals(type)) {
                if(!flag) {
                    System.out.println(type + ":");
                    System.out.println(products[i].header());
                }

                System.out.println(products[i]);
                flag = true;
            }
        }

        if(!flag) {
            System.out.println("No " + type + " products!");
        }
    }

    // input and check the int number entered
    public int inputIntNumber(String prompt) {
        String line;

        while (true) {
            try {
                System.out.print(prompt);
                line = scanner.nextLine();

                if(line == null || line.trim().isEmpty()) {
                    System.out.println("Empty value");
                    continue;
                }

                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
    }

    // input and check the float number entered
    public float inputFloatNumber(String prompt) {
        String line;

        while (true) {
            try {
                System.out.print(prompt);
                line = scanner.nextLine();

                if(line == null || line.trim().isEmpty()) {
                    System.out.println("Empty value");
                    continue;
                }

                return Float.parseFloat(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
    }

    // input and check the string entered
    public String inputString(String prompt) {
        String line;

        while (true) {
            System.out.print(prompt);
            line = scanner.nextLine();

            if(line == null || line.trim().isEmpty()) {
                System.out.println("Empty value");
                continue;
            }

            return line.trim();
        }
    }

    /**
     * input a new electronic and return it
     * @return Electronics
     */
    public Electronics inputElectronics() {
        int id = inputIntNumber("Enter ID: ");
        if(getIndexByID(id) != -1) {
            System.out.println("Invalid ID");
            return null;
        }

        String name = inputString("Enter name: ");
        float price = inputFloatNumber("Enter price: ");
        String brand = inputString("Enter brand: ");

        return new Electronics(id, name, price, brand);
    }

    /**
     * input a new clothing and return it
     * @return Clothing
     */
    public Clothing inputClothing() {
        int id = inputIntNumber("Enter ID: ");
        if(getIndexByID(id) != -1) {
            System.out.println("Invalid ID");
            return null;
        }

        String name = inputString("Enter name: ");
        float price = inputFloatNumber("Enter price: ");
        String size = inputString("Enter size: ");

        return new Clothing(id, name, price, size);
    }

    /**
     * Choose product and create a new one
     * @return Product
     */
    public Product createProduct() {
        System.out.println("Which product do you want to create?");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();
        if(choice == null || choice.trim().isEmpty()) {
            System.out.println("Empty choice");
            return null;
        } else if(choice.trim().equals("1")) {
            return inputElectronics();
        } else if(choice.trim().equals("2")) {
            return inputClothing();
        } else {
            System.out.println("Invalid choice");
            return null;
        }
    }

    // main program
    public static void main(String[] args) {
        Program program = new Program();

        while(true) {
            System.out.println("------ Product operations ------");
            System.out.println("1. Add product");
            System.out.println("2. Display products");
            System.out.println("3. Find product by ID");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            if(choice == null || choice.trim().isEmpty()) {
                System.out.println("Empty choice");
            } else {
                switch (choice) {
                    case "1":
                        Product product = program.createProduct();
                        program.addProduct(product);
                        break;
                    case "2":
                        program.displayProducts();
                        break;
                    case "3":
                        int id = program.inputIntNumber("Enter ID: ");
                        Product result = program.findProduct(id);
                        if(result != null) {
                            System.out.println(result.getType() + ":");
                            System.out.println(result.header());
                            System.out.println(result);
                        } else {
                            System.out.println("No product found!");
                        }
                        break;
                    case "0":
                        System.out.print("Are you sure to exit? Please enter 1 to confirm! (1/0): ");
                        String opt = scanner.nextLine();
                        if(opt != null && opt.trim().equals("1")) {
                            scanner.close();
                            System.exit(0);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        }
    }
}
