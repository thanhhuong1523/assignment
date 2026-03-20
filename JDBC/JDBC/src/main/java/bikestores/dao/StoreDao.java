package bikestores.dao;

import bikestores.dto.StoreDto;
import bikestores.models.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoreDao implements IDao <Store, StoreDto> {
    private final Connection conn;

    public StoreDao (Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Store> selectAll(String name, String address, String phone) throws SQLException {
        ArrayList<Store> listStores = new ArrayList<>();
        if(conn == null) return listStores;

        StringBuilder sql = new StringBuilder("SELECT * FROM stores WHERE 1=1");

        if(name != null) sql.append(" AND name LIKE ?");
        if(address != null) sql.append(" AND address LIKE ?");
        if(phone != null) sql.append(" AND phone LIKE ?");

        try (PreparedStatement ps = conn.prepareStatement(String.valueOf(sql))) {
            int index = 1;

            if(name != null) ps.setString(index++, "%" + name + "%");
            if(address != null) ps.setString(index++, "%" + address + "%");
            if(phone != null) ps.setString(index, "%" + phone + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listStores.add(getFromResultSet(rs));
                }
                return listStores;
            }
        }
    }

    @Override
    public Store getByID(int id) throws SQLException {
        if(conn == null) return null;

        String sql = "SELECT * FROM stores WHERE store_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? getFromResultSet(rs) : null;
            }
        }
    }

    @Override
    public Store getFromResultSet(ResultSet rs) throws SQLException {
        if(conn == null) return null;

        int storeID = rs.getInt("store_id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String phone = rs.getString("phone");

        return new Store(storeID, name, address, phone);
    }

    @Override
    public int insert(StoreDto dto) throws SQLException {
        if(conn == null) return -1;

        String sql = "INSERT INTO stores (name, address, phone) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setString(index++, dto.getName());
            ps.setString(index++, dto.getAddress());
            ps.setString(index, dto.getPhone());

            if(ps.executeUpdate() == 0) return -1;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
    }

    @Override
    public boolean update(int id, StoreDto dto) throws SQLException {
        if(conn == null) return false;

        String sql = """
                    UPDATE stores
                    SET name = ?, address = ?, phone = ?
                    WHERE store_id = ?
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, dto.getName());
            ps.setString(index++, dto.getAddress());
            ps.setString(index++, dto.getPhone());
            ps.setInt(index, id);

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if(conn == null) return false;

        String sql = "DELETE FROM stores WHERE store_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean isDuplicateName(String name, int id) throws SQLException {
        if(conn == null) return false;

        String sql = "SELECT 1 FROM stores WHERE name = ? AND store_id <> ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, name);
            ps.setInt(index, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean isDuplicateAddress(String address, int id) throws SQLException {
        if(conn == null) return false;

        String sql = "SELECT 1 FROM stores WHERE address = ? AND store_id <> ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, address);
            ps.setInt(index, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean isReferencedByOtherTables(int id) throws SQLException {
        if(conn == null) return false;

        String sqlWithStaffs = "SELECT 1 FROM staffs WHERE store_id = ?";
        String sqlWithStocks = "SELECT 1 FROM stocks WHERE store_id = ?";

        try (PreparedStatement ps1 = conn.prepareStatement(sqlWithStaffs);
            PreparedStatement ps2 = conn.prepareStatement(sqlWithStocks)) {

            ps1.setInt(1, id);
            try (ResultSet rs = ps1.executeQuery()) {
                if(rs.next()) return true;
            }

            ps2.setInt(1, id);
            try (ResultSet rs = ps2.executeQuery()) {
                return rs.next();
            }
        }
    }
}
