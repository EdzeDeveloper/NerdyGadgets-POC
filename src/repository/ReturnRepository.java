package repository;
import model.Return;
import repository.interfaces.CrudInterface;
import model.DBConnection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ReturnRepository implements CrudInterface<Return> {
    private Connection con = DBConnection.getConnection();

    public void create(Return obj) throws SQLException {
        String query
                = "INSERT INTO retour (bestellingID, reden) " +
                "VALUES (?, ?);";
        PreparedStatement preparedStatement
                = con.prepareStatement(query);

        preparedStatement.setInt(1, obj.getBestellingID());
        preparedStatement.setString(2, obj.getReden());

        int rowsAffected = preparedStatement.executeUpdate();

        //  Get the last inserted ID and set the retour ID to the obj.
        String lastIdQuery
                = "SELECT LAST_INSERT_ID() as last_id;";
        PreparedStatement lastIdStatement
                = con.prepareStatement(lastIdQuery);

        ResultSet lastIdResult = lastIdStatement.executeQuery();
        while (lastIdResult.next()) {
            obj.setRetourID(lastIdResult.getInt("last_id"));
        }

        //insert products into product-return pivot table.
        Map<Integer, Integer> returnedProducts = obj.getReturnedProducts();
        returnedProducts.forEach((productID, quantity) -> {
            try {
                String pivotQuery
                        = "INSERT INTO retourproducten (productID, retourID, aantal) " +
                            "VALUES (?, ?, ?);";

                PreparedStatement pivotStatement
                        = con.prepareStatement(pivotQuery);

                pivotStatement.setInt(1, productID);
                pivotStatement.setInt(2, obj.getRetourID());
                pivotStatement.setInt(3, quantity);

                pivotStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

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
