package bikestores.dao;

import bikestores.dto.StaffDto;
import bikestores.models.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffDao implements IDao<Staff, StaffDto> {
    private final Connection conn;

    public StaffDao(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Staff> selectAll(String name, String role, int storeID) throws SQLException {
        ArrayList<Staff> listStaffs = new ArrayList<>();

        if(conn == null) return listStaffs;

        StringBuilder sql = new StringBuilder("""
                SELECT sf.*, se.name AS store_name
                FROM staffs sf
                LEFT JOIN stores se ON sf.store_id = se.store_id
                WHERE 1=1
                """);

        if(name != null) sql.append(" AND sf.name LIKE ?");
        if(role != null) sql.append(" AND sf.role = ?");
        if(storeID != -1) sql.append(" AND sf.store_id = ?");

        try (PreparedStatement ps = conn.prepareStatement(String.valueOf(sql))) {
            int index = 1;

            if (name != null) ps.setString(index++, "%" + name + "%");
            if (role != null) ps.setString(index++, role);
            if (storeID != -1) ps.setInt(index++, storeID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Staff staff = getFromResultSet(rs);
                    listStaffs.add(staff);
                }
                return listStaffs;
            }
        }
    }

    @Override
    public Staff getByID(int id) throws SQLException {
        if(conn == null) return null;

        String sql = """
            SELECT sf.*, se.name AS store_name
            FROM staffs sf 
            LEFT JOIN stores se ON sf.store_id = se.store_id
            WHERE sf.staff_id = ?
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? getFromResultSet(rs) : null;
            }
        }
    }

    @Override
    public Staff getFromResultSet(ResultSet rs) throws SQLException {
        int staffID = rs.getInt("staff_id");
        String staffName = rs.getString("name");
        String role = rs.getString("role");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        int storeID = rs.getInt("store_id");
        String storeName = rs.getString("store_name");

        return new Staff(staffID, staffName, role, email, phone, storeID, storeName);
    }

    @Override
    public int insert(StaffDto staffDto) throws SQLException {
        if(conn == null) return -1;

        String sql = "INSERT INTO staffs(name, role, email, phone, store_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setString(index++, staffDto.getName());
            ps.setString(index++, staffDto.getRole());
            ps.setString(index++, staffDto.getEmail());
            ps.setString(index++, staffDto.getPhone());
            ps.setInt(index++, staffDto.getStoreID());

            if(ps.executeUpdate() == 0) return -1;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
    }

    @Override
    public boolean update(int id, StaffDto staffDto) throws SQLException {
        if(conn == null) return false;

        String sql = "UPDATE staffs " +
                    "SET name = ?, role = ?, email = ?, phone = ?, store_id = ? " +
                    "WHERE staff_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            ps.setString(index++, staffDto.getName());
            ps.setString(index++, staffDto.getRole());
            ps.setString(index++, staffDto.getEmail());
            ps.setString(index++, staffDto.getPhone());
            ps.setInt(index++, staffDto.getStoreID());
            ps.setInt(index++, id);

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if(conn == null) return false;

        String sql = "DELETE FROM staffs WHERE staff_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean isDuplicateEmail(String email, int id) throws SQLException {
        if(conn == null) return false;

        String sql = "SELECT 1 FROM staffs WHERE email = ? AND staff_id <> ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            ps.setString(index++, email);
            ps.setInt(index++, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean isDuplicatePhone(String phone, int id) throws SQLException {
        if(conn == null) return false;

        String sql = "SELECT 1 FROM staffs WHERE phone = ? AND staff_id <> ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            ps.setString(index++, phone);
            ps.setInt(index++, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean isExistedStoreID(int storeID) throws SQLException {
        if(conn == null) return false;

        String sql = "SELECT 1 FROM stores WHERE store_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, storeID);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
