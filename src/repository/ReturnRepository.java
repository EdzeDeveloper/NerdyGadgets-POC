package repository;
import model.Return;
import repository.interfaces.CrudInterface;
import model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnRepository<T> implements CrudInterface<Return>{
    private Connection con = DBConnection.getConnection();

    @Override
    public void add(Return obj) throws SQLException {
      // TODO Auto-generated method stub
      
    }

    @Override
    public Return find(int id) throws SQLException {
      String query
      = "select * from retour where retourID= ?";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);

      preparedStatement.setInt(1, id);
      Return returnInstance = new Return();
      ResultSet resultSet = preparedStatement.executeQuery();
      boolean check = false;

      while (resultSet.next()) {
          check = true;
          returnInstance.setBestellingID(resultSet.getInt("bestellingID"));
          returnInstance.setReden(resultSet.getString("reden"));
          returnInstance.setRetourID(resultSet.getInt("retourID"));
      }

      if (check == true) {
          return returnInstance;
      }
      return null;
    }

    @Override
    public void Update() throws SQLException {
      // TODO Auto-generated method stub
        
      // String query
      // = "update Return set emp_name=?, "
      //   + " emp_address= ? where emp_id = ?";
      // PreparedStatement ps
      // = con.prepareStatement(query);
      // ps.setString(1, emp.getEmp_name());
      // ps.setString(2, emp.getEmp_address());
      // ps.setInt(3, emp.getEmp_id());
      // ps.executeUpdate();
    }

    @Override
    public void Delete(String key) throws SQLException {
      // TODO Auto-generated method stub
      
    }

    @Override
    public ResultSet getAll() throws SQLException {
      String query
      = "select * from retour";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);

      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet;
    }
  }
