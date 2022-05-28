package model;


import repository.EdgeRepository;
import repository.OrderRepository;
import repository.RouteRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class Route {

  private int routeId;
  private double aantalkm;
  private int graphID;

  public Route(int graphID){
    this.routeId = graphID;
    this.graphID = graphID;
    this.aantalkm = -1;
  }

  public Route(){

  }

  public int getRouteId() {
    return routeId;
  }

  public void setRouteId(int routeId) {
    this.routeId = routeId;
  }

  public double getAantalkm() {
    return aantalkm;
  }

  public void setAantalkm(double aantalkm) {
    this.aantalkm = aantalkm;
  }

  public int getGraphID() {
    return graphID;
  }

  public void setGraphID(int graphID) {
    this.graphID = graphID;
  }

  public ArrayList<Order> nearestNeighbor() throws SQLException {
    ArrayList<Order> deliveryOrder = new ArrayList<>();

    OrderRepository orderRepository = new OrderRepository();
    ArrayList<Order> nodeList = orderRepository.getNodesInGraph(this.graphID);

    EdgeRepository edgeRepository = new EdgeRepository();
    double aantalkm = 0;

    // Add the first node to the list
//    deliveryOrder.add(nodeList.get(0));
    // Add NerdyGadgets warehouse as first and last node
    Order nerdyGadgetsWarehouse = orderRepository.get(-1);
    deliveryOrder.add(0, nerdyGadgetsWarehouse);

    // Compile a list of all edges connected to the current node
    for (int delivery = 0; delivery < nodeList.size(); delivery++) {


      // get a list of edges that are connected to the current node
      ArrayList<Edge> edgesConnectedToNode = edgeRepository.getEdgesConnectedToNode(deliveryOrder.get(delivery));

      // for every node connected to current node, check if it's closer than others
      double lowestCostEdgeCost = -1;
      Order nextNode = null;
      for (Edge edge : edgesConnectedToNode) {
        if (edge.getStartNodeID() == -2 || edge.getTargetNodeID() == -2){
          continue;
        }
        double cost = edge.getCost();
        if (lowestCostEdgeCost == -1 || cost < lowestCostEdgeCost) {
          // get the node id (can be startNodeID or targetNodeID)
          int edgeStartNodeID = edge.getStartNodeID();
          int edgeTargetNodeID = edge.getTargetNodeID();

          int nextNodeTemp;

          if (deliveryOrder.get(delivery).getBestellingID() == edgeStartNodeID){
            nextNodeTemp = edgeTargetNodeID;
          } else {
            nextNodeTemp = edgeStartNodeID;
          }

          // Check if the node is already in the deliverylist
          boolean nodeIsInDeliveryOrder = false;
          for (Order deliveryOrderNode : deliveryOrder) {
            if (deliveryOrderNode.getBestellingID() == nextNodeTemp) {
              nodeIsInDeliveryOrder = true;
              break;
            }
          }

          if (!nodeIsInDeliveryOrder) {
            nextNode = orderRepository.get(nextNodeTemp);
            lowestCostEdgeCost = cost;
          }

        }
        if (cost == 0){
          break;
        }
      }
      aantalkm += lowestCostEdgeCost;
      deliveryOrder.add(nextNode);
    }

    Order nerdyGadgetsWarehouse2 = orderRepository.get(-2);
    Edge finalEdge = edgeRepository.getEdgeConnectingTwoNodes(deliveryOrder.get(deliveryOrder.size() -1) , nerdyGadgetsWarehouse2);
    deliveryOrder.add(nerdyGadgetsWarehouse2);
    aantalkm += finalEdge.getCost();

    setAantalkm(aantalkm);
    return deliveryOrder;
  }

  public ArrayList<Order> twoOpt() throws SQLException {
    // get the deliveryOrder
    RouteRepository routeRepository = new RouteRepository();
    ArrayList<Order> deliveryOrder = routeRepository.getDeliveryOrder(this.routeId);

//     Perform 2-changes for every edge
    for (int delivery = 0; delivery < deliveryOrder.size()- 1; delivery++) {
      Order startNode1 = deliveryOrder.get(delivery);
      Order targetNode1 = deliveryOrder.get(delivery + 1);

      double currentCost = -1;
      double costSaved = 0;

      // get the current edge information
      EdgeRepository edgeRepository = new EdgeRepository();
      double currentEdgeCost1 = edgeRepository.getEdgeConnectingTwoNodes(startNode1, targetNode1).getCost();

      // save the current best edge cost
      double bestCost = -1;
      Order bestAlternateNode = null;

      // loop through all nodes and perform a 2-change if possible
      for (int node = 0; node < deliveryOrder.size() - 1; node++) {
        Order startNode2 = deliveryOrder.get(node);
        Order targetNode2 = deliveryOrder.get(node + 1);

        // check if there is a possible 2-change
        if (startNode1 == startNode2 || startNode1 == targetNode2 || targetNode1 == startNode2 || targetNode1 == targetNode2) {
          continue;
        }

        // get the edge cost of the possible 2-change
        double currentEdgeCost2 = edgeRepository.getEdgeConnectingTwoNodes(startNode2, targetNode2).getCost();

        //save current situation
        currentCost = currentEdgeCost1 + currentEdgeCost2;
        if (bestCost == -1) {
          bestCost = currentCost;
        }

        // perform a 2-change
        // get the edge cost from startNode1 to startNode2
        double targetEdgeCost1 = edgeRepository.getEdgeConnectingTwoNodes(startNode1, startNode2).getCost();

        // get the edge cost from targetNode1 to targetNode2
        double targetEdgeCost2 = edgeRepository.getEdgeConnectingTwoNodes(targetNode1, targetNode2).getCost();

        // save target situation
        double targetCost = targetEdgeCost1 + targetEdgeCost2;

        // compare the old situation to the new situation, if its better switch the targets
        if (targetCost < currentCost){
          bestCost = targetCost;
          costSaved = currentCost - bestCost;
          bestAlternateNode = startNode2;
        }
      }

      // check if there is a better 2-change, if not go to next delivery
      if (bestAlternateNode == null){
        continue;
      }

      // if there is a better 2-change, edit the route
      int firstNodeIndex = -1;
      int secondNodeIndex = -1;
      for (int deliveryNumber = 0; deliveryNumber < deliveryOrder.size() - 1; deliveryNumber++) {
        if (deliveryOrder.get(deliveryNumber) == startNode1 || deliveryOrder.get(deliveryNumber) == bestAlternateNode){
          if (firstNodeIndex == -1) {
            firstNodeIndex = deliveryNumber + 1;
          } else {
            secondNodeIndex = deliveryNumber + 1;
            break;
          }
        }
      }

      // grab the part to be reversed
      Collections.reverse(deliveryOrder.subList(firstNodeIndex, secondNodeIndex));
      routeRepository.updateDeliveryOrder(deliveryOrder, this.routeId);
      this.aantalkm -= costSaved;
    }
    return deliveryOrder;
  }
}
