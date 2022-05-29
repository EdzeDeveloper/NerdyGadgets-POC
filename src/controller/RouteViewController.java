package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import model.Graph;
import model.Order;
import model.Route;
import repository.DatabaseActions;
import repository.RouteRepository;
import view.RouteView;

public class RouteViewController {
	
	private RouteView routeView;
	private Route route;
	
	public RouteViewController(RouteView routeView) throws SQLException {
		this.routeView = routeView;

		this.routeView.setCalculateRouteListener(new RouteViewListener());
		this.routeView.setCalculateFor2OptRouteListener(new RouteViewListener());
	}
	
	class RouteViewListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
      if("Bereken route".equals(e.getActionCommand())) {
        try {
          JTextField TextFieldAmountOfOrderNumbers = routeView.getAmountOfOrderNumber();
          if(TextFieldAmountOfOrderNumbers.getText().isEmpty()){
            routeView.displayErrorMessage("Vul aantal bestellingen in.");
          } else if(Integer.parseInt(TextFieldAmountOfOrderNumbers.getText()) <= 0) {
            routeView.displayErrorMessage("Aantal bestellingen mag niet minder of gelijk zijn aan 0.");
          } else {
            routeView.displayErrorMessage("De route wordt voor u berekend.");
            routeView.createRouteList(calculateRouteNearestNeigbor(Integer.parseInt(TextFieldAmountOfOrderNumbers.getText())), route);
          }
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          routeView.displayErrorMessage("Er is wat mis gegaan met het berekenen van de route");
          e1.printStackTrace();
        }
      }
      if("Bereken 2-opt".equals(e.getActionCommand())) {
        try {
          routeView.displayErrorMessage("De 2-opt berekening probeert uw route te verbeteren.");
          routeView.create2optList(calculateRoute2opt(routeView.getCurrentRoute()), routeView.getRoute());
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          routeView.displayErrorMessage("Er is wat mis gegaan met het berekenen van de 2-opt route");
          e1.printStackTrace();
        }
      }
		}

    private ArrayList calculateRouteNearestNeigbor(int numberOfNodes) throws SQLException {
      DatabaseActions databaseActions = new DatabaseActions();
      System.out.println("Creating graph...");
      long startTime = System.nanoTime();
      databaseActions.emptyGraphFromDatabase("SET foreign_key_checks = 0; TRUNCATE TABLE graph; TRUNCATE TABLE graphnodes; TRUNCATE TABLE graphedges; TRUNCATE TABLE edge; TRUNCATE TABLE route; TRUNCATE TABLE bestellingenlijst;");
      Graph graph = new Graph(1);
      graph.createGraph(numberOfNodes);
      long endTime = System.nanoTime();
      long duration = (endTime - startTime)/1000000000;
      System.out.println("Created graph with " + numberOfNodes + " nodes in " + duration + " seconds.");

      System.out.println("");

      System.out.println("Creating route with Nearest Neighbor...");
      startTime = System.nanoTime();
      RouteRepository routeRepository = new RouteRepository();

      route = new Route(1);
      
      routeRepository.create(route);
      ArrayList<Order> deliveryOrder = route.nearestNeighbor();
      routeRepository.update(route);
      routeRepository.addDeliveryOrder(deliveryOrder, route.getRouteId());
      endTime = System.nanoTime();
      duration = (endTime - startTime)/1000000000;
      System.out.println("Completed Nearest Neighbor in " + duration + " seconds. " + Math.round(route.getAantalkm()/1000) + " km");

      System.out.println("");
      return deliveryOrder;
    }

    public ArrayList calculateRoute2opt(ArrayList<Order> neirestNeighborOrders) throws SQLException {
      System.out.println("Applying 2-opt...");
      RouteRepository routeRepository = new RouteRepository();

      //set time variables
      long startTime = System.nanoTime();
      long endTime = System.nanoTime();
      long duration = (endTime - startTime)/1000000000;

      startTime = System.nanoTime();
      double currentAantalkm = route.getAantalkm();
      neirestNeighborOrders = route.twoOpt();
      routeRepository.update(route);
      routeRepository.updateDeliveryOrder(neirestNeighborOrders, route.getRouteId());
      double newAantalkm = route.getAantalkm();
      endTime = System.nanoTime();
      duration = (endTime - startTime)/1000000000;
      if (newAantalkm == currentAantalkm) {
        System.out.println("2-opt found the best route " + Math.round(newAantalkm/1000) + " km");
      }
      System.out.println("Completed 2-opt iteration " + duration + " seconds. " + Math.round(route.getAantalkm()/1000) + " km");
      return neirestNeighborOrders;
    }
	}
}
