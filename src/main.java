import java.sql.SQLException;

import controller.ReturnController;
import view.ReturnView;

public class main {
    
  public static void main(String[] args) throws SQLException {
    
    // CalculatorView theView = new CalculatorView();
      
    // CalculatorModel theModel = new CalculatorModel();
      
    // CalculatorController theController = new CalculatorController(theView,theModel);
      
    // theView.setVisible(true);

    ReturnView returnView = new ReturnView();
      
    ReturnController returnController = new ReturnController(returnView);
      
    returnView.setVisible(true);
  }
}