package sales.dao;

import sales.entities.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {
    private Connection conn;

    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(Customer customer) throws SQLException {
        if(conn == null) {
            return false;
        }

        String sql = "INSERT INTO customers(customer_name, contact_name, address, city, postal_code, country) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            int index = 1;

            ps.setString(index++, customer.getName());
            ps.setString(index++, customer.getContact());
            ps.setString(index++, customer.getAddress());
            ps.setString(index++, customer.getCity());
            ps.setString(index++, customer.getPostCode());
            ps.setString(index++, customer.getCountry());

            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot add new customer: ", e);
        }

        return false;
    }

    public boolean update(int id, Customer customer) throws SQLException {
        if(conn == null) {
            return false;
        }

        String sql = "UPDATE customers " +
                    "SET customer_name = ?, contact_name = ?, address = ?, city = ?, postal_code = ?, country = ?" +
                    "WHERE customer_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, customer.getName());
            ps.setString(index++, customer.getContact());
            ps.setString(index++, customer.getAddress());
            ps.setString(index++, customer.getCity());
            ps.setString(index++, customer.getPostCode());
            ps.setString(index++, customer.getCountry());
            ps.setInt(index++, id);

            int rs = ps.executeUpdate();

            return rs > 0;
        } catch (SQLException e) {
            throw new SQLException("Cannot update this customer: ", e);
        }
    }

    public boolean delete(int id) throws SQLException {
        if(conn == null) {
            return false;
        }

        String deleteInOrders = "DELETE FROM orders WHERE customer_id = ?";
        String deleteInCustomers = "DELETE FROM customers WHERE customer_id = ?";

        try {
            conn.setAutoCommit(false);

            try(PreparedStatement ps1 = conn.prepareStatement(deleteInOrders);
                PreparedStatement ps2 = conn.prepareStatement(deleteInCustomers)) {
                ps1.setInt(1, id);
                ps1.executeUpdate();

                ps2.setInt(1, id);
                int rs = ps2.executeUpdate();

                return rs > 0;
            }
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException("Cannot delete this customer: ", e);
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public ArrayList<Customer> selectAll() throws SQLException {
        if(conn == null) {
            return null;
        }

        String sql = "SELECT * FROM Customers";
        ArrayList<Customer> listCustomers = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setContact(rs.getString("contact_name"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setPostCode(rs.getString("postal_code"));
                customer.setCountry(rs.getString("country"));

                listCustomers.add(customer);
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot display customers", e);
        }

        return listCustomers;
    }

    public Customer getByID(int id) throws SQLException {
        if(conn == null) {
            return null;
        }

        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("customer_name");
                    String contact = rs.getString("contact_name");
                    String address = rs.getString("address");
                    String city = rs.getString("city");
                    String postCode = rs.getString("postal_code");
                    String country = rs.getString("country");

                    return new Customer(id, name, contact, address, city, postCode, country);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot get this customer: ", e);
        }

        return null;
    }
}
