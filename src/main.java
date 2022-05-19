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
    // newDatabaseConnection.insertUpdateDelete("INSERT INTO persoon (persoonID, voornaam, achternaam, adresID, email) VALUES ('69420', 'edze', 'van der werff', '3889', 's1177499@student.windesheim.nl')");
    // newDatabaseConnection.insertUpdateDelete("DELETE FROM persoon WHERE persoonID = '69420'");
    // newDatabaseConnection.insertUpdateDelete("UPDATE persoon SET voornaam = 'hotty42069' WHERE persoonID = '69420'");
  }
}