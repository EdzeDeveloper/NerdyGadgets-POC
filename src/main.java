import java.sql.SQLException;
import java.util.ArrayList;

import model.*;
import repository.*;

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

    newDatabaseConnection.insertUpdateDelete("SET foreign_key_checks = 0; TRUNCATE TABLE route; TRUNCATE TABLE bestellingenlijst;");

    RouteRepository routeRepository = new RouteRepository();
    Route route = new Route(1);
    routeRepository.add(route);
    ArrayList<Order> deliveryOrder = route.nearestNeighbor();
    routeRepository.Update(route);
    routeRepository.addDeliveryOrder(deliveryOrder, route.getRouteId());
    System.out.println("Nearest Neighbor:" + route.getAantalkm() + " meter");

    for (int i = 1; i < 4; i++) {
      double currentAantalkm = route.getAantalkm();
      deliveryOrder = route.twoOpt();
      routeRepository.Update(route);
      routeRepository.updateDeliveryOrder(deliveryOrder, route.getRouteId());
      double newAantalkm = route.getAantalkm();
      if (newAantalkm == currentAantalkm) {
        System.out.println("2-opt found the best route in " + (i - 1) + " itterations: " + newAantalkm + " meter");
        break;
      }
      System.out.println("2-opt iteration " + i + ": " + route.getAantalkm() + " meter");
    }


  }
}