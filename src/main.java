import java.sql.ResultSet;
import java.sql.SQLException;

import controller.ReturnOrderListController;
import model.newDatabaseConnection;
// import model.databaseConnection;
import view.ReturnedOrdersListView;
public class main {
  public static void main(String[] args) throws SQLException {
    ReturnedOrdersListView returnOrderListView = new ReturnedOrdersListView();
      
    ReturnOrderListController controller = new ReturnOrderListController(returnOrderListView);

    ResultSet getAllPersonData = newDatabaseConnection.read("Select * from Persoon limit 10");
    while(getAllPersonData.next()){
      System.out.println(getAllPersonData.getString("voornaam"));
    }
    System.out.println(getAllPersonData);

  }
}