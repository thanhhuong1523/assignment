package bikestores.utils;

public class Constants {
    public static String STAFF_HEADERS() {
        return String.format("%-8s | %-15s | %-10s | %-20s | %-15s | %-10s | %-15s",
                "Staff ID", "Name", "Role", "Email", "Phone", "Store ID", "Store Name");
    }

    public static String STORE_HEADERS() {
        return String.format("%-10s | %-15s | %-15s | %-15s",
                "Store ID", "Name", "Address", "Phone");
    }

    public static String CUSTOMER_HEADERS() {
        return String.format("%-15s | %-20s | %-10s | %-15s | %-15s",
                "Customer ID", "Name", "Gender", "Phone", "Email");
    }
}
