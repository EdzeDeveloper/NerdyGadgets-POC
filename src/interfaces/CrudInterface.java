package interfaces;
  
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public void delete(int id)
    throws SQLException;
}