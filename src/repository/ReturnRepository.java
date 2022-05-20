package repository;
import model.Return;
// import model.Order;
import model.newDatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class ReturnRepository{
  // create query
      
  public static ArrayList<Return> getAllReturnedOrders() throws SQLException {
		ResultSet result = newDatabaseConnection.select("SELECT * FROM retour");
    ArrayList returnedOrders = new ArrayList<Return>();
    while (result.next()) {
      Return returnModel = new Return(); 

      returnModel.setRetourID(result.getInt("retourID")); 
      returnModel.setReden(result.getString("reden")); 
      returnModel.setBestellingID(result.getInt("bestellingID")); 

      returnedOrders.add(returnModel);
    }
    return returnedOrders;
  }
}
