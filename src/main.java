import java.sql.SQLException;

import controller.CalculatorController;
import model.CalculatorModel;
import model.Order;
import model.databaseConnection;
import repository.OrderRepository;
import view.CalculatorView;

public class main {
    
  public static void main(String[] args) throws SQLException {
    
    CalculatorView theView = new CalculatorView();
      
    CalculatorModel theModel = new CalculatorModel();
      
    CalculatorController theController = new CalculatorController(theView,theModel);
      
    theView.setVisible(true);

    //orderRepository and model test.
    OrderRepository orderRepository = new OrderRepository();
    Order order = orderRepository.findById(2);
    System.out.println(order.getPersoonID());
  }
}