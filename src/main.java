import java.sql.SQLException;
import controller.MainController;


public class main {
  public static void main(String[] args) throws SQLException {

    new MainController();

    // int numberOfNodes = 10;

    // System.out.println("Creating graph...");
    // long startTime = System.nanoTime();
    // newDatabaseConnection.insertUpdateDelete("SET foreign_key_checks = 0; TRUNCATE TABLE graph; TRUNCATE TABLE graphnodes; TRUNCATE TABLE graphedges; TRUNCATE TABLE edge;");
    // Graph graph = new Graph(1);
    // graph.createGraph(numberOfNodes);
    // long endTime = System.nanoTime();
    // long duration = (endTime - startTime)/1000000000;
    // System.out.println("Created graph with " + numberOfNodes + " nodes in " + duration + " seconds.");

    // System.out.println("");

    // System.out.println("Creating route with Nearest Neighbor...");
    // startTime = System.nanoTime();
    // newDatabaseConnection.insertUpdateDelete("SET foreign_key_checks = 0; TRUNCATE TABLE route; TRUNCATE TABLE bestellingenlijst;");
    // RouteRepository routeRepository = new RouteRepository();

    // Route route = new Route(1);
    
    // routeRepository.create(route);
    // ArrayList<Order> deliveryOrder = route.nearestNeighbor();
    // routeRepository.Update(route);
    // routeRepository.addDeliveryOrder(deliveryOrder, route.getRouteId());
    // endTime = System.nanoTime();
    // duration = (endTime - startTime)/1000000000;
    // System.out.println("Completed Nearest Neighbor in " + duration + " seconds. " + Math.round(route.getAantalkm()/1000) + " km");

    // System.out.println("");

    // System.out.println("Applying 2-opt...");
    // for (int i = 1; i < 4; i++) {
    //   startTime = System.nanoTime();
    //   double currentAantalkm = route.getAantalkm();
    //   deliveryOrder = route.twoOpt();
    //   routeRepository.Update(route);
    //   routeRepository.updateDeliveryOrder(deliveryOrder, route.getRouteId());
    //   double newAantalkm = route.getAantalkm();
    //   endTime = System.nanoTime();
    //   duration = (endTime - startTime)/1000000000;
    //   if (newAantalkm == currentAantalkm) {
    //     System.out.println("2-opt found the best route in " + (i - 1) + " itterations " + Math.round(newAantalkm/1000) + " km");
    //     break;
    //   }
    //   System.out.println("Completed 2-opt iteration " + i + " in " + duration + " seconds. " + Math.round(route.getAantalkm()/1000) + " km");
    // }

    // System.out.println("Klaar!");
  }
}