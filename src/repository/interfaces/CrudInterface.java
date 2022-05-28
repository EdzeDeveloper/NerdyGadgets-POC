package repository.interfaces;
  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudInterface<T>
{
    public void add(T obj)
    throws SQLException;

    public T find(int id)
    throws SQLException;
    
    public ResultSet getAll()
    throws SQLException;
    
    public void Update()
    throws SQLException;
    
    public void Delete(String key)
    throws SQLException;
}