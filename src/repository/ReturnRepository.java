package repository;
import model.Return;
import repository.interfaces.CrudInterface;
import model.DBConnection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnRepository implements CrudInterface<Return> {
    private Connection con = DBConnection.getConnection();

    public void create(Return obj) throws SQLException {
      // TODO Auto-generated method stub
      
    }

    public Return find(int id) throws SQLException {
      Return returnInstance = new Return();

      String query
          = "select * from retour where retourID= ?";
      PreparedStatement preparedStatement
          = con.prepareStatement(query);

      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
          returnInstance.setBestellingID(resultSet.getInt("bestellingID"));
          returnInstance.setReden(resultSet.getString("reden"));
          returnInstance.setRetourID(resultSet.getInt("retourID"));
      }

      //We don't need a check, when we can't find the ID in db, we just return an empty object.
      return returnInstance;
    }

    public void update(Return obj) throws SQLException {
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

    public void delete(String key) throws SQLException {
      // TODO Auto-generated method stub
      
    }

    public ArrayList<Return> findAll(String key) throws SQLException {
      // // TODO Auto-generated method stubString query = "select * from Return";
      // PreparedStatement ps
      //     = con.prepareStatement(query);
      // ResultSet rs = ps.executeQuery();
      // List<Return> ls = new ArrayList();

      // while (rs.next()) {
      //     Return emp = new Return();
      //     // emp.setEmp_id(rs.getInt("emp_id"));
      //     // emp.setEmp_name(rs.getString("emp_name"));
      //     // emp.setEmp_address(rs.getString("emp_address"));
      //     ls.add(emp);
      // }
      // return ls;
      return null;
    }
      
  // public static ArrayList<Return> getAllReturnedOrders() throws SQLException {
	// 	ResultSet result = DBConnection.select("SELECT * FROM retour");
  //   ArrayList returnedOrders = new ArrayList<Return>();
  //   while (result.next()) {
  //     int bestellingID = result.getInt("bestellingID"); 
  //     String reden = result.getString("reden"); 
  //     int retourID = result.getInt("retourID"); 
  //     Return returnModel = new Return(bestellingID, reden, retourID); 

  //     returnedOrders.add(returnModel);
  //   }
  //   return returnedOrders;
  // }
}
