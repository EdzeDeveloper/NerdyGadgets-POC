import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import controller.CalculatorController;
import model.*;
import repository.*;
import view.CalculatorView;

public class main {
    
  public static void main(String[] args) throws SQLException {
    
    // CalculatorView theView = new CalculatorView();
      
    // CalculatorModel theModel = new CalculatorModel();
      
    // CalculatorController theController = new CalculatorController(theView,theModel);

    // databaseConnection dbconnect = new databaseConnection();

    // graphModel testGraph = new graphModel(100);

//    newDatabaseConnection.insertUpdateDelete("SET foreign_key_checks = 0; TRUNCATE TABLE bestelling; TRUNCATE TABLE bestellingenlijst; TRUNCATE TABLE besteldeproducten;");
    OrderRepository orderRepository = new OrderRepository();
    orderRepository.generateOrders(100);
//    orderRepository.generateOrders(100);
//    orderRepository.generateOrders(100);
//    orderRepository.generateOrders(100);
//    orderRepository.generateOrders(100);
//    orderRepository.generateOrders(100);


    // theView.setVisible(true);
  }
}