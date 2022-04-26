import java.sql.SQLException;

import controller.CalculatorController;
import model.CalculatorModel;
import model.databaseConnection;
import view.CalculatorView;

public class main {
    
  public static void main(String[] args) throws SQLException {
    
    CalculatorView theView = new CalculatorView();
      
    CalculatorModel theModel = new CalculatorModel();
      
    CalculatorController theController = new CalculatorController(theView,theModel);

    databaseConnection dbconnect = new databaseConnection();
      
      theView.setVisible(true);
  }
}