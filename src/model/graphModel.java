package model;

import javax.xml.transform.Result;
import java.sql.*;

public class graphModel {

    private int graphID;
    private int[] nodeList;
    private edgeModel[] edgeList;


    public graphModel(int graphID, int numberOfNodes) {
        this.graphID = graphID;
        this.nodeList = new int[numberOfNodes];

        // calculate number of edges (summation/sigma)
        int numberOfEdges = 0;
        for (int i = 1; i <= numberOfNodes - 1; i++) {
            numberOfEdges = numberOfEdges + i;
        }
        this.edgeList = new edgeModel[numberOfEdges];

        try {
            createGraph();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Created a graph with " + numberOfNodes + " nodes, and " + numberOfEdges + " edges.");
    }

    public void createGraph() throws SQLException {

        int startNodeID, startNodeIDX, startNodeIDY, targetNodeID, targetNodeIDX, targetNodeIDY;
        int edgeID = 0;
        int nodeID = 0;
        double cost;
        boolean edgeExists;

        // insert graphID into database
        newDatabaseConnection.insertUpdateDelete("INSERT INTO graph (graphID) VALUES " + '(' + this.graphID + ')');

        // Get random adresses
        ResultSet bestellingen = newDatabaseConnection.select("SELECT * FROM bestelling JOIN adres on leverAdresId = adresID ORDER BY RAND() LIMIT " + this.nodeList.length);

        // 3. Calculate cost of every edge
        // add every node to the nodeList
        while (bestellingen.next()) {
            nodeList[nodeID] = bestellingen.getInt("bestellingID");
            nodeID++;
        }

        // insert every node in the database
        String nodeInsertValues = "";
        for (int node : nodeList) {
            nodeInsertValues = nodeInsertValues + "INSERT INTO graphnodes (graphID, nodeID) VALUES " + "(" + this.graphID + "," + node + ");";
        }
        newDatabaseConnection.insertUpdateDelete(nodeInsertValues);


        // for every node
        for (int startNodeIndex = 1; startNodeIndex < this.nodeList.length + 1; startNodeIndex++) {
            // get the x and y coordinates
            bestellingen.absolute(startNodeIndex); // absolute() sets the active row in the table, has to be set manually because we switch back and forth between entries
            startNodeID = bestellingen.getInt("bestellingID");
            startNodeIDX = bestellingen.getInt("x");
            startNodeIDY = bestellingen.getInt("y");

            // for every other node also take the x and y coordinates
            for (int targetNodeIndex = 1; targetNodeIndex < this.nodeList.length + 1; targetNodeIndex++) {

                if (startNodeIndex != targetNodeIndex) {
                    bestellingen.absolute(targetNodeIndex); // absolute() sets the active row in the table, has to be set manually because we switch back and forth between entries
                    targetNodeID = bestellingen.getInt("bestellingID");
                    // check if the edge is already in the database

                    ResultSet edgeInDatabase = newDatabaseConnection.select("SELECT * FROM edge WHERE (startNodeID = " + startNodeID + " AND targetNodeID = " + targetNodeID + ") OR (startNodeID = " + targetNodeID + " AND targetNodeID = " + startNodeID + ")");
                    edgeExists = false;
                    while(edgeInDatabase.next()){
                        edgeExists = true;
                    }

                    if (!edgeExists) {
                        targetNodeIDX = bestellingen.getInt("x");
                        targetNodeIDY = bestellingen.getInt("y");

                        // calculate the distance between the nodes with the hypotenuse. Both x and y distance must be positive (absolute)
                        cost = Math.hypot(Math.abs(startNodeIDX - targetNodeIDX), Math.abs(startNodeIDY - targetNodeIDY));

                        edgeModel edge = new edgeModel(edgeID, startNodeID, targetNodeID, cost);
                        edgeList[edgeID] = edge;

                        // TODO insert into database
                        newDatabaseConnection.insertUpdateDelete("INSERT INTO edge (edgeID, startNodeID, targetNodeID, cost) VALUES (" + edgeID + ", " + startNodeID + ", " + targetNodeID +  ", " + cost + "); INSERT INTO graphedges (graphID, edgeID) VALUES (" + graphID + ", " + edgeID + ");");

                        edgeID++;
                    }
                }
            }
        }
    }

    public int[] getNodeList() {
        return nodeList;
    }

    public edgeModel[] getEdgeList() {
        return edgeList;
    }
}