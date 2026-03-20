package bikestores.dao;

import bikestores.models.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDao<E, D> {

    E getByID(int id) throws SQLException;

    E getFromResultSet (ResultSet rs) throws SQLException ;

    int insert(D dto) throws SQLException;

    boolean update(int id, D dto) throws SQLException;

    boolean delete(int id) throws SQLException;
}
