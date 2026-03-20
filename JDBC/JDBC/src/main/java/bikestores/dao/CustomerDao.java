package bikestores.dao;

import bikestores.dto.CustomerDto;
import bikestores.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDao implements IDao <Customer, CustomerDto> {
    private final Connection conn;

    public CustomerDao (Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Customer> selectAll(int id, String name, String phone, String email) throws SQLException {
        ArrayList<Customer> listCustomers = new ArrayList<>();
        if(conn == null) return listCustomers;

        StringBuilder sql = new StringBuilder("SELECT * FROM customers WHERE 1=1");

        if(id != -1) sql.append(" AND customer_id = ?");
        if(name != null) sql.append(" AND name LIKE ?");
        if(phone != null) sql.append(" AND phone LIKE ?");
        if(email != null) sql.append(" AND email LIKE ?");

        try (PreparedStatement ps = conn.prepareStatement(String.valueOf(sql))) {
            int index = 1;

            if(id != -1) ps.setInt(index++, id);
            if(name != null) ps.setString(index++, "%" + name + "%");
            if(phone != null) ps.setString(index++, "%" + phone + "%");
            if(email != null) ps.setString(index++, "%" + email + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listCustomers.add(getFromResultSet(rs));
                }
                return listCustomers;
            }
        }
    }

    @Override
    public Customer getByID(int id) throws SQLException {
        if(conn == null) return null;

        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? getFromResultSet(rs) : null;
            }
        }
    }

    @Override
    public Customer getFromResultSet(ResultSet rs) throws SQLException {
        if(conn == null) return null;

        int id = rs.getInt("customer_id");
        String name = rs.getString("name");
        String gender = rs.getString("gender");
        String email = rs.getString("email");
        String phone = rs.getString("phone");

        return new Customer(id, name, gender, phone, email);
    }

    @Override
    public int insert(CustomerDto dto) throws SQLException {
        if(conn == null) return -1;

        String sql = "INSERT INTO customers (name, gender, phone, email) VALUES (?, ?, ?, ?) ";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            ps.setString(index++, dto.getName());
            ps.setString(index++, dto.getGender());
            ps.setString(index++, dto.getPhone());
            ps.setString(index, dto.getEmail());

            if(ps.executeUpdate() == 0) return -1;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
    }

    @Override
    public boolean update(int id, CustomerDto dto) throws SQLException {
        if(conn == null) return false;

        String sql = "UPDATE customers SET name = ?, gender = ?, phone = ?, email = ? WHERE customer_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            ps.setString(index++, dto.getName());
            ps.setString(index++, dto.getGender());
            ps.setString(index++, dto.getPhone());
            ps.setString(index++, dto.getEmail());
            ps.setInt(index, id);

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if(conn == null) return false;

        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean isDuplicateEmail (String email, int id) throws SQLException {
        if(conn == null) return false;

        String sql = "SELECT 1 FROM customers WHERE email = ? AND customer_id <> ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, email);
            ps.setInt(index, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean isDuplicatePhone (String phone, int id) throws SQLException {
        if(conn == null) return false;

        String sql = "SELECT 1 FROM customers WHERE phone = ? AND customer_id <> ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, phone);
            ps.setInt(index, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean isReferencedByOtherTables(int id) throws SQLException {
        if(conn == null) return false;

        String sqlWithOrders = "SELECT 1 FROM orders WHERE customer_id = ?";
        String sqlWithWishlists = "SELECT 1 FROM wishlists WHERE customer_id = ?";
        String sqlWithReviews = "SELECT 1 FROM reviews WHERE customer_id = ?";

        try (PreparedStatement ps1 = conn.prepareStatement(sqlWithOrders);
             PreparedStatement ps2 = conn.prepareStatement(sqlWithWishlists);
             PreparedStatement ps3 = conn.prepareStatement(sqlWithReviews)) {

            ps1.setInt(1, id);
            try (ResultSet rs = ps1.executeQuery()) {
                if(rs.next()) return true;
            }

            ps2.setInt(1, id);
            try (ResultSet rs = ps2.executeQuery()) {
                if(rs.next()) return true;
            }

            ps3.setInt(1, id);
            try (ResultSet rs = ps3.executeQuery()) {
                return rs.next();
            }
        }
    }
}
