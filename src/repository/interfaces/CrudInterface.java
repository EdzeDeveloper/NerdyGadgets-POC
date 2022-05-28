package repository.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudInterface<T>
{
    public void create(T obj)
            throws SQLException;

    public T find(int id)
            throws SQLException;

    public ArrayList<T> getAll(String key)
            throws SQLException;

    public void Update(T obj)
            throws SQLException;

    public void Delete(String key)
            throws SQLException;
}