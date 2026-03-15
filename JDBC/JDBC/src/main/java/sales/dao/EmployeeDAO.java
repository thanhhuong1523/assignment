package sales.dao;

import sales.entities.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO implements IDAO<Employee> {
    private final Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<Employee> selectAll() throws SQLException {
        if(conn == null) return null;

        ArrayList<Employee> listEmployees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String birthday = String.valueOf(rs.getDate("birth_day"));
                int supervisor = rs.getInt("supervisor_id");

                listEmployees.add(new Employee(id, lastName, firstName, birthday, supervisor));
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot get all employees: " + e.getMessage());
        }

        return listEmployees;
    }

    @Override
    public Employee getByID(int id) throws SQLException {
        if(conn == null) return null;

        String sql = "SELECT * FROM employees WHERE employee_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    String lastName = rs.getString("last_name");
                    String firstName = rs.getString("first_name");
                    String birthday = String.valueOf(rs.getDate("birth_day"));
                    int supervisor = rs.getInt("supervisor_id");

                    return new Employee(id, lastName, firstName, birthday, supervisor);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot get this customer: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean insert(Employee employee) throws SQLException {
        if(conn == null) return false;

        String sql = "{call sp_add_employee(?, ?, ?, ?)}";
        int index = 1;

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(index++, employee.getLastName());
            cs.setString(index++, employee.getFirstName());
            cs.setString(index++, employee.getBirthday());
            cs.setInt(index++, employee.getSupervisor());

            int rs = cs.executeUpdate();
            if(rs > 0) return true;

        } catch (SQLException e) {
            throw new SQLException("Cannot add this employee: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(int id, Employee employee) throws SQLException {
        if(conn == null) return false;

        String sql = "UPDATE employees " +
                    "SET last_name = ?, first_name = ?, birth_day = ?, supervisor_id = ? " +
                    "WHERE employee_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;

            ps.setString(index++, employee.getLastName());
            ps.setString(index++, employee.getFirstName());
            ps.setString(index++, employee.getBirthday());
            ps.setInt(index++, employee.getSupervisor());
            ps.setInt(index, id);

            if(ps.executeUpdate() > 0) return true;

        } catch (SQLException e) {
            throw new SQLException("Cannot update this employee: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if(conn == null) return false;

        conn.setAutoCommit(false);

        String deleteInOrders = "DELETE FROM orders WHERE employee_id = ?";
        String deleteInEmployees = "DELETE FROM employees WHERE employee_id = ?";

        try (PreparedStatement ps1 = conn.prepareStatement(deleteInOrders);
            PreparedStatement ps2 = conn.prepareStatement(deleteInEmployees)) {
            ps1.setInt(1, id);
            ps1.executeUpdate();

            ps2.setInt(1, id);
            int rs = ps2.executeUpdate();

            conn.commit();

            if(rs > 0) return true;
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException("Cannot delete this employee: " + e.getMessage());
        } finally {
            conn.setAutoCommit(true);
        }

        return false;
    }
}
