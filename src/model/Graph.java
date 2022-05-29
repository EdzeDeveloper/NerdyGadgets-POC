package model;

import repository.AdresRepository;
import repository.EdgeRepository;
import repository.GraphRepository;
import repository.OrderRepository;

import java.sql.*;
import java.util.ArrayList;

public class Graph {

    private int graphID;

    public Graph() {

    }

    public Graph(int graphID) throws SQLException {
        this.graphID = graphID;
    }

	public void createGraph(int numberOfNodes) throws SQLException {

        EdgeRepository edgeRepository = new EdgeRepository();
        OrderRepository orderRepository = new OrderRepository();
        AdresRepository adresRepository = new AdresRepository();
        GraphRepository graphRepository = new GraphRepository();
        ArrayList<Edge> edgeList = new ArrayList<>();

        int startNodeID, startNodeIDX, startNodeIDY, targetNodeID, targetNodeIDX, targetNodeIDY;
        int edgeID = 0;
        int createdEdgesCount = 0;
        double cost;
        boolean edgeExists;

        // Insert graph into database
        graphRepository.create(this);

        // Get random adresses and add them to nodeList
        ArrayList<Order> nodeList = orderRepository.getRandomOrdersWithAdres(numberOfNodes);
        orderRepository.addGraphNodes(nodeList, this.graphID);

        // Add NerdyGadgets warehouse as first and last node
        Order nerdyGadgetsWarehouse = orderRepository.find(-1);
        nodeList.add(0, nerdyGadgetsWarehouse);
        Order nerdyGadgetsWarehouse2 = orderRepository.find(-2);
        nodeList.add(nerdyGadgetsWarehouse2);


        // Calculate cost of every edge
        for (int startNodeIndex = 0; startNodeIndex < nodeList.size(); startNodeIndex++) {
            // get the x and y coordinates
            Order startNode = nodeList.get(startNodeIndex);
            Adres startNodeAdres = adresRepository.find(startNode.getAdresID());
            startNodeID = startNode.getBestellingID();
            startNodeIDX = startNodeAdres.getX();
            startNodeIDY = startNodeAdres.getY();

            // for every other node also take the x and y coordinates
            for (int targetNodeIndex = 0; targetNodeIndex < nodeList.size(); targetNodeIndex++) {

                if (startNodeIndex != targetNodeIndex) {
                    Order targetNode = nodeList.get(targetNodeIndex);
                    targetNodeID = targetNode.getBestellingID();
                    // check if the edge is already in the database

                    edgeExists = false;
                    for (int edge = 0; edge < createdEdgesCount; edge++) {
                        if ((edgeList.get(edge).getStartNodeID() == startNodeID && edgeList.get(edge).getTargetNodeID() == targetNodeID) ||
                                (edgeList.get(edge).getTargetNodeID() == startNodeID && edgeList.get(edge).getStartNodeID() == targetNodeID)) {
                            edgeExists = true;
                            break;
                        }
                    }
                    if (!edgeExists) {
                        Adres targetNodeAdres = adresRepository.find(targetNode.getAdresID());
                        targetNodeIDX = targetNodeAdres.getX();
                        targetNodeIDY = targetNodeAdres.getY();

                        // calculate the distance between the nodes with the hypotenuse. Both x and y distance must be positive (absolute)
                        cost = Math.hypot(Math.abs(startNodeIDX - targetNodeIDX), Math.abs(startNodeIDY - targetNodeIDY));

                        Edge edge = new Edge(edgeID, startNodeID, targetNodeID, cost);
                        edgeList.add(edgeID, edge);
                        // insert into database
                        edgeRepository.create(edge);
                        edgeID++;
                        createdEdgesCount++;
                    }
                }
            }
        }
        // Add the graphEdges to the database
        edgeRepository.addGraphEdges(edgeList, this.graphID);
    }

    public int getGraphID() {
        return graphID;
    }

    public void setGraphID(int graphID) {
        this.graphID = graphID;
    }
}