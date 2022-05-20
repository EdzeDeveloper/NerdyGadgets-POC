import java.sql.SQLException;
import model.*;
import view.*;

public class main {
    
  public static void main(String[] args) throws SQLException {
    
//    CalculatorView theView = new CalculatorView();
//
//    CalculatorModel theModel = new CalculatorModel();
//
//    CalculatorController theController = new CalculatorController(theView,theModel);
//
//    databaseConnection dbconnect = new databaseConnection();
//
//      theView.setVisible(true);
    newDatabaseConnection.insertUpdateDelete("SET foreign_key_checks = 0; TRUNCATE TABLE graph");
    graphModel graph = new graphModel(1, 100);
  }
}