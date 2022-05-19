import java.sql.SQLException;

import model.*;
import repository.RouteRepository;

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
//    theView.setVisible(true);

    RouteRepository routeRepository = new RouteRepository(100);
    Route route = routeRepository.nearestNeighbor();
    System.out.println(route.getAantalkm());

    routeRepository.twoOpt();

  }
}