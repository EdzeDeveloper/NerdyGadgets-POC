import java.sql.SQLException;

import controller.MainController;
import controller.ReturnProductsController;
import model.Order;
import model.Return;
import repository.OrderRepository;
import repository.ReturnRepository;
import view.ReturnProductsView;

public class main {
  public static void main(String[] args) throws SQLException {

    new MainController();


    // ReturnedOrdersListView returnOrderListView = new ReturnedOrdersListView(mainFrame);
      
    // new ReturnOrderListController(returnOrderListView);

    // ResultSet getAllPersonData = newDatabaseConnection.select("Select * from Persoon limit 10");
    // while(getAllPersonData.next()){
    //   System.out.println(getAllPersonData.getString("voornaam"));
    // }
    // newDatabaseConnection.insertUpdateDelete("INSERT INTO persoon (persoonID, voornaam, achternaam, adresID, email) VALUES ('69420', 'edze', 'van der werff', '3889', 's1177499@student.windesheim.nl')");
    // newDatabaseConnection.insertUpdateDelete("DELETE FROM persoon WHERE persoonID = '69420'");
    // newDatabaseConnection.insertUpdateDelete("UPDATE persoon SET voornaam = 'hotty42069' WHERE persoonID = '69420'");
  }
}