import java.sql.SQLException;

import model.Order;
import model.Return;
import repository.OrderRepository;
import repository.ReturnRepository;

public class main {
  public static void main(String[] args) throws SQLException {

    // new MainController();
    ReturnRepository returnRepo = new ReturnRepository();
    Return Retour  = returnRepo.find(1);
    System.out.print(Retour.getReden());

    OrderRepository orderRepo = new OrderRepository();
    Order Order  = orderRepo.find(1, true);
    System.out.print(Order.getBesteldeProducten().toString());
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