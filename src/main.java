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

    OrderRepository orderRepository = new OrderRepository();
    Order[] orderlist = orderRepository.generateOrders(8);

    // theView.setVisible(true);
  }
}