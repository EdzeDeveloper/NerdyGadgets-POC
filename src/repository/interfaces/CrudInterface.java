package repository.interfaces;
  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudInterface<T>
{
    public void create(T obj)
    throws SQLException;

    public T find(int id)
    throws SQLException;
    
    public ResultSet findAll()
    throws SQLException;
    
    public void update(T obj)
    throws SQLException;
    
    public void delete(String key)
    throws SQLException;
}