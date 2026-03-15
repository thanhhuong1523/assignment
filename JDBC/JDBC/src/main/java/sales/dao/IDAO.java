package sales.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    List<T> selectAll() throws SQLException;

    T getByID(int id) throws SQLException;

    boolean insert(T t) throws SQLException;

    boolean update(int id, T t) throws SQLException;

    boolean delete(int id) throws SQLException;
}
