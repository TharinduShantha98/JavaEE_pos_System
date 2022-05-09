package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T,ID> {

    boolean add(T t);
    boolean delete(ID id);
    boolean update(T t);
    T search(ID id);
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;


}
