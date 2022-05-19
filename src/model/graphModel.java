package model;

import java.sql.*;

public class graphModel {

    private int graphID;
    private int[] nodeList;
    private edgeModel[] edgeList;


    public graphModel(int numberOfNodes) {
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

        // Get random adresses
        ResultSet result = newDatabaseConnection.read("SELECT * FROM adres ORDER BY RAND() LIMIT " + this.nodeList.length);


        // 3. Calculate cost of every edge
        int startNodeID, startNodeIDX, startNodeIDY, targetNodeID, targetNodeIDX, targetNodeIDY;
        int edgeID = 0;
        int nodeID = 0;
        int createdEdgesCount = 0;
        double cost;
        boolean edgeExists;

        // add every node to the nodeList
        while (result.next()) {
            // System.out.println(nodeID);
            nodeList[nodeID] = result.getInt("adresID");
            nodeID++;
        }

        // for every node
        for (int startNodeIndex = 1; startNodeIndex < this.nodeList.length + 1; startNodeIndex++) {
            // get the x and y coordinates
            result.absolute(startNodeIndex); // absolute() sets the active row in the table, has to be set manually because we switch back and forth between entries
            startNodeID = result.getInt("adresID");
            startNodeIDX = result.getInt("x");
            startNodeIDY = result.getInt("y");

            // for every other node also take the x and y coordinates
            for (int targetNodeIndex = 1; targetNodeIndex < this.nodeList.length + 1; targetNodeIndex++) {

                if (startNodeIndex != targetNodeIndex) {
                    result.absolute(targetNodeIndex); // absolute() sets the active row in the table, has to be set manually because we switch back and forth between entries
                    targetNodeID = result.getInt("adresID");
                    // check if the edge is already in the database

                    edgeExists = false;
                    for (int edge = 0; edge < createdEdgesCount; edge++) {
                        if ((edgeList[edge].getStartNodeID() == startNodeID && edgeList[edge].getTargetNodeID() == targetNodeID) ||
                                (edgeList[edge].getTargetNodeID() == startNodeID && edgeList[edge].getStartNodeID() == targetNodeID)) {
                            edgeExists = true;
                            break;
                        }
                    }
                    if (!edgeExists) {
                        targetNodeIDX = result.getInt("x");
                        targetNodeIDY = result.getInt("y");

                        // calculate the distance between the nodes with the hypotenuse. Both x and y distance must be positive (absolute)
                        cost = Math.hypot(Math.abs(startNodeIDX - targetNodeIDX), Math.abs(startNodeIDY - targetNodeIDY));

                        edgeModel edge = new edgeModel(edgeID, startNodeID, targetNodeID, cost);
                        edgeList[edgeID] = edge;
                        // TODO insert into database
                        // System.out.println(startNodeID + " - " + targetNodeID);
                        // System.out.println(cost);
                        // System.out.println("");
                        edgeID++;
                        createdEdgesCount++;
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